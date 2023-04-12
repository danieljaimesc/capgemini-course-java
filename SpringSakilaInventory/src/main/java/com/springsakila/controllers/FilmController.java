package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Film;
import com.springsakila.inventory.domain.services.FilmServiceImpl;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/film")
public class FilmController {
    @Autowired
    private FilmServiceImpl filmService;

    @GetMapping("/{id}")
    public FilmDetailsDTO get(@PathVariable int id) throws NotFoundException {
        var film = filmService.getOne(id);
        if (film.isEmpty()) throw new NotFoundException();
        return FilmDetailsDTO.from(film.get());
    }

    //Create record if not exist and if exist update it
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDetailsDTO postCreate(@Valid @RequestBody FilmDetailsDTO filmDetailsDTO) throws InvalidDataException,
            DuplicateKeyException {
        return  FilmDetailsDTO.from(filmService.add(FilmDetailsDTO.from(filmDetailsDTO)));
    }

    @PatchMapping("/{id}")
    public FilmDetailsDTO update(@PathVariable int id, @Valid @RequestBody FilmDetailsDTO filmDetailsDTO) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (filmService.getOne(id).isEmpty()) throw new BadRequestException("Id not exist.");
        return FilmDetailsDTO.from(filmService.modify(FilmDetailsDTO.from(filmDetailsDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws NotFoundException {
        if (filmService.getOne(id).isEmpty()) throw new NotFoundException();
        filmService.deleteById(id);
    }

    @GetMapping("/{id}/characters")
    public List<CharacterDTO> getCharacterList(@PathVariable int id) throws NotFoundException {
        Optional<Film> film = filmService.getOne(id);
        if (film.isEmpty()) throw new NotFoundException();
        return film.get().getCharacters().stream().map(CharacterDTO::from).toList();
    }

    @GetMapping("/{id}/categories")
    public List<Category> getCategoryList(@PathVariable int id) throws NotFoundException {
        Optional<Film> film = filmService.getOne(id);
        if (film.isEmpty()) throw new NotFoundException();
        return film.get().getCategories();
    }
}
