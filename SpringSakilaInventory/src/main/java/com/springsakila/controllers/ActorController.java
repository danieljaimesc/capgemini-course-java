package com.springsakila.controllers;

import com.springsakila.inventory.domain.contracts.services.ActorService;
import com.springsakila.inventory.domain.entities.Actor;
import com.springsakila.inventory.infrastructure.dto.ActorDTO;
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
@RequestMapping("/api/v1/actors")
public class ActorController {
    @Autowired
    private ActorService characterService;

    @GetMapping("/{id}")
    public ActorDTO get(@PathVariable int id) throws NotFoundException {
        var character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return ActorDTO.from(character.get());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDTO postCreate(@Valid @RequestBody ActorDTO characterDTO) throws InvalidDataException,
            DuplicateKeyException {
        return ActorDTO.from(characterService.add(ActorDTO.from(characterDTO)));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ActorDTO update(@PathVariable int id, @Valid @RequestBody ActorDTO characterDTO) throws BadRequestException, InvalidDataException, NotFoundException {
        if (characterService.getOne(id).isEmpty()) throw new BadRequestException("Id not exist.");
        return ActorDTO.from(characterService.modify(ActorDTO.from(id, characterDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws NotFoundException {
        if (characterService.getOne(id).isEmpty()) throw new NotFoundException();
        characterService.deleteById(id);
    }

    @GetMapping
    public List<ActorDTO> getAll(@RequestParam(required = false) String sort) {
        System.out.println("HOLA");
        return sort != null ? (List<ActorDTO>) characterService.getByProjection(Sort.by(sort),
                ActorDTO.class) : characterService.getByProjection(ActorDTO.class);
    }

    @GetMapping(params = "page")
    public Page<ActorDTO> getAll(Pageable pageable) {
        return characterService.getByProjection(pageable, ActorDTO.class);
    }

    @GetMapping("/{id}/films")
    public List<FilmShortDTO> getFilmList(@PathVariable int id) throws NotFoundException {
        Optional<Actor> character = characterService.getOne(id);
        if (character.isEmpty()) throw new NotFoundException();
        return character.get().getFilmActors().stream().map(item -> FilmShortDTO.from(item.getFilm())).toList();
    }
}
