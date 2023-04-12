package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Film;
import com.springsakila.inventory.domain.services.FilmServiceImpl;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmDTO;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/film")
public class FilmController {
    @Autowired
    private FilmServiceImpl filmService;

    @GetMapping("/{id}")
    public FilmDTO get(@PathVariable int id) throws NotFoundException {
        var film = filmService.getOne(id);
        if (film.isEmpty()) throw new NotFoundException();
        return FilmDTO.from(film.get());
    }

    //Create record if not exist and if exist update it
    @PostMapping
    public FilmDTO postCreate(@RequestBody FilmDTO filmDTO) throws InvalidDataException, BadRequestException,
            NotFoundException, DuplicateKeyException {
        FilmDTO filmResult;
        if (filmService.getOne(filmDTO.getFilmId()).isPresent()) filmResult = update(filmDTO.getFilmId(), filmDTO);
        else filmResult = FilmDTO.from(filmService.add(FilmDTO.from(filmDTO)));
        return filmResult;
    }

    @PatchMapping("/{id}")
    public FilmDTO update(@PathVariable int id, @RequestBody FilmDTO filmDTO) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != filmDTO.getFilmId()) throw new BadRequestException("Identifiers do not match");
        return FilmDTO.from(filmService.modify(FilmDTO.from(filmDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
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
