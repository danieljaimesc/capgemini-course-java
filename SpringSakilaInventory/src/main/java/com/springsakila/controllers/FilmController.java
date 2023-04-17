package com.springsakila.controllers;

import com.springsakila.inventory.domain.contracts.services.FilmService;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Film;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.PaginationConverter;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/{id}")
    public FilmDetailsDTO get(@PathVariable int id) throws NotFoundException {
        var film = filmService.getOne(id);
        if (film.isEmpty()) throw new NotFoundException();
        return FilmDetailsDTO.from(film.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDetailsDTO postCreate(@Valid @RequestBody FilmDetailsDTO filmDetailsDTO) throws InvalidDataException,
            DuplicateKeyException {
        return FilmDetailsDTO.from(filmService.add(FilmDetailsDTO.from(filmDetailsDTO)));
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

    @GetMapping
    public Page<FilmShortDTO> getAll(Pageable pageable, @RequestParam(defaultValue = "short") String mode) {
        return filmService.getByProjection(pageable, FilmShortDTO.class);
    }


    @GetMapping(params = "mode=details")
    @Transactional
    public Page<FilmDetailsDTO> getAllDetails(@Parameter(hidden = true) Pageable pageable,
                                              @RequestParam(defaultValue = "short") String mode) {
        List<FilmDetailsDTO> filmDetailsDTOList = filmService.getAll().stream().map(FilmDetailsDTO::from).toList();
        return PaginationConverter.paginateList(pageable, filmDetailsDTOList);
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
