package com.springsakila.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsakila.controllers.CharacterController;
import com.springsakila.inventory.domain.contracts.services.CharacterService;
import com.springsakila.inventory.domain.entities.Character;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CharacterService srv;

    private String urlTemplate;

    @BeforeEach
    void setUp() throws Exception {
        urlTemplate = "/api/v1/character";
    }

    @Test
    void testGetOne() throws Exception {
        int id = 1;
        var character = new Character(id, "Pepito", "Grillo");

        when(srv.getOne(id)).thenReturn(Optional.of(character));
        mockMvc.perform(get(urlTemplate + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value(character.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(character.getLastName()))
                .andDo(print());
    }

    @Test
    void testGetOne404() throws Exception {
        int id = 1;
        when(srv.getOne(id)).thenReturn(Optional.empty());
        mockMvc.perform(get(urlTemplate + "/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Not Found"))
                .andDo(print());
    }

    @Test
    void testCreate() throws Exception {
        var ele = new Character(1, "PEPITO", "GRILLO");
        when(srv.add(ele)).thenReturn(ele);
        mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CharacterDTO.from(ele)))
                )
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }

    @Value
    static class CharacterShortMock {
        int characterId;
        String firstName;
        String lastName;
    }
}
