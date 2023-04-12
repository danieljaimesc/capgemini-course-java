package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Character;
import com.springsakila.inventory.domain.services.CharacterServiceImpl;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterCollectionController {
    @Autowired
    private CharacterServiceImpl characterService;

    @GetMapping
    public List<CharacterDTO> getAll(@RequestParam(required = false) String sort) {
        List<Character> characterList = sort != null ? (List<Character>) characterService.getByProjection(Sort.by(sort),
                Character.class) : characterService.getByProjection(Character.class);
        return characterList.stream().map(CharacterDTO::from).toList();
    }

    @GetMapping(params = "page")
    public Page<CharacterDTO> getAll(Pageable pageable) {
        return characterService.getByProjection(pageable, CharacterDTO.class);
    }
}
