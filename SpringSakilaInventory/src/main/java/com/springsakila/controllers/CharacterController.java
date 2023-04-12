package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Character;
import com.springsakila.inventory.domain.services.CharacterServiceImpl;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/character")
public class CharacterController {
    @Autowired
    private CharacterServiceImpl characterService;

    @GetMapping(path = "/{id}")
    public CharacterDTO get(@PathVariable int id) throws NotFoundException {
        var character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return CharacterDTO.from(character.get());
    }

    //Create record if not exist and if exist update it
    @PostMapping()
    public CharacterDTO postCreate(@RequestBody CharacterDTO characterDTO) throws InvalidDataException,
            DuplicateKeyException, BadRequestException, NotFoundException {
        CharacterDTO characterResult;
        if (characterService.getOne(characterDTO.getCharacterId()).isPresent())
            characterResult = update(characterDTO.getCharacterId(), characterDTO);
        else characterResult = CharacterDTO.from(characterService.add(CharacterDTO.from(characterDTO)));
        return characterResult;
    }

    @PatchMapping("/{id}")
    public CharacterDTO update(@PathVariable int id, @RequestBody CharacterDTO characterDTO) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != characterDTO.getCharacterId()) throw new BadRequestException("Identifiers do not match");
        return CharacterDTO.from(characterService.modify(CharacterDTO.from(characterDTO)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        characterService.deleteById(id);
    }

    @GetMapping("/{id}/films")
    public List<FilmShortDTO> getFilmList(@PathVariable int id) throws NotFoundException {
        Optional<Character> character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return character.get().getFilmCharacters().stream().map(item -> FilmShortDTO.from(item.getFilm())).toList();
    }
}
