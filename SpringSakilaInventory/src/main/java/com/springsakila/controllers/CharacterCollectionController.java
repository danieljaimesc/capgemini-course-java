package com.springsakila.controllers;

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
        return sort != null ? (List<CharacterDTO>) characterService.getByProjection(Sort.by(sort),
                CharacterDTO.class) : characterService.getByProjection(CharacterDTO.class);
    }

    @GetMapping(params = "page")
    public Page<CharacterDTO> getAll(Pageable pageable) {
        return characterService.getByProjection(pageable, CharacterDTO.class);
    }
}
