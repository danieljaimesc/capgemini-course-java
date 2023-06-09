package com.springsakila.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsakila.controllers.ActorController;
import com.springsakila.inventory.domain.contracts.services.ActorService;
import com.springsakila.inventory.infrastructure.dto.ActorDTO;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActorController.class)
public class CharacterCollectionControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ActorService srv;

    private String urlTemplate;

    @BeforeEach
    void setUp() throws Exception {
        urlTemplate = "/api/v1/actors";
    }

    @Test
    void testGetAllString() throws Exception {
        List<ActorDTO> list = new ArrayList<>(
                Stream.of(
                        new CharacterShortMock(1, "Pepito", "Grillo"),
                        new CharacterShortMock(2, "Carmelo", "Coton"),
                        new CharacterShortMock(3, "Capitan", "Tan")
                ).map(item -> new ActorDTO(item.characterId, item.firstName, item.lastName)).toList()
        );
        when(srv.getByProjection(ActorDTO.class)).thenReturn(list);
        mockMvc.perform(get(urlTemplate).accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        jsonPath("$.size()").value(3)
                );
    }

    @Test
    void testGetAllPageable() throws Exception {
        List<ActorDTO> list = new ArrayList<>(
                Stream.of(
                        new CharacterShortMock(1, "Pepito", "Grillo"),
                        new CharacterShortMock(2, "Carmelo", "Coton"),
                        new CharacterShortMock(3, "Capitan", "Tan")
                ).map(item -> new ActorDTO(item.characterId, item.firstName, item.lastName)).toList()
        );

        when(srv.getByProjection(PageRequest.of(0, 20), ActorDTO.class))
                .thenReturn(new PageImpl<>(list));
        mockMvc.perform(get(urlTemplate).queryParam("page", "0"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
                        jsonPath("$.content.size()").value(3),
                        jsonPath("$.size").value(3)
                );
    }

    @Value
    static class CharacterShortMock {
        int characterId;
        String firstName;
        String lastName;
    }
}
