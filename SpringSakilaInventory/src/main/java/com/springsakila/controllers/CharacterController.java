package com.springsakila.controllers;

import com.springsakila.inventory.domain.contracts.services.CharacterService;
import com.springsakila.inventory.domain.entities.Character;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping("/{id}")
    public CharacterDTO get(@PathVariable int id) throws NotFoundException {
        var character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return CharacterDTO.from(character.get());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO postCreate(@Valid @RequestBody CharacterDTO characterDTO) throws InvalidDataException,
            DuplicateKeyException {
        return CharacterDTO.from(characterService.add(CharacterDTO.from(characterDTO)));
    }

    @PatchMapping("/{id}")
    @Transactional
    public CharacterDTO update(@PathVariable int id, @Valid @RequestBody CharacterDTO characterDTO) throws BadRequestException, InvalidDataException, NotFoundException {
        if (characterService.getOne(id).isEmpty()) throw new BadRequestException("Id not exist.");
        return CharacterDTO.from(characterService.modify(CharacterDTO.from(id, characterDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws NotFoundException {
        if (characterService.getOne(id).isEmpty()) throw new NotFoundException();
        characterService.deleteById(id);
    }

    @GetMapping
    public List<CharacterDTO> getAll(@RequestParam(required = false) String sort) {
        return sort != null ? (List<CharacterDTO>) characterService.getByProjection(Sort.by(sort),
                CharacterDTO.class) : characterService.getByProjection(CharacterDTO.class);
    }

    @GetMapping(params = "page")
    public Page<CharacterDTO> getAll(Pageable pageable) {
        return characterService.getByProjection(pageable, CharacterDTO.class);
    }

    @GetMapping("/{id}/films")
    public List<FilmShortDTO> getFilmList(@PathVariable int id) throws NotFoundException {
        Optional<Character> character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return character.get().getFilmCharacters().stream().map(item -> FilmShortDTO.from(item.getFilm())).toList();
    }
}
