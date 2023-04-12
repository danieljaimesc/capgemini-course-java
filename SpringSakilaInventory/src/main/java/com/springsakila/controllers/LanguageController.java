package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.domain.services.LanguageServiceImpl;
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
@RequestMapping("/api/v1")
public class LanguageController {
    @Autowired
    private LanguageServiceImpl languageService;

    @GetMapping("/language/{id}")
    public Language get(@PathVariable int id) throws NotFoundException {
        var language = languageService.getOne(id);
        if (language.isEmpty()) throw new NotFoundException();
        return language.get();
    }

    @PostMapping("/language")
    @ResponseStatus(HttpStatus.CREATED)
    public Language postCreate(@Valid @RequestBody Language language) throws InvalidDataException, BadRequestException,
            DuplicateKeyException {
        return languageService.add(language);
    }

    @PatchMapping("/language/{id}")
    public Language update(@PathVariable int id, @Valid @RequestBody Language language) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != language.getLanguageId()) throw new BadRequestException("Identifiers do not match");
        return languageService.modify(language);
    }

    @DeleteMapping("/language/{id}")
    public void deleteById(@PathVariable int id) {
        languageService.deleteById(id);
    }

    @GetMapping("/languages")
    public List<Language> getAll() {
        return languageService.getAll();
    }

    @GetMapping("/language/{id}/films")
    @Transactional
    public Page<FilmShortDTO> getFilmShortList(Pageable pageable, @RequestParam(defaultValue = "short") String mode,
                                               @PathVariable int id) throws NotFoundException {
        Optional<Language> language = languageService.getOne(id);
        if (language.isEmpty()) throw new NotFoundException();
        List<FilmShortDTO> filmShortList = language.get().getFilms().stream().map(FilmShortDTO::from).toList();
        return PaginationConverter.paginateList(pageable, filmShortList);
    }

    @GetMapping(path = "/language/{id}/films", params = "mode=details")
    @Transactional
    public Page<FilmDetailsDTO> getFilmDetailsList(@Parameter(hidden = true) Pageable pageable,
                                                   @RequestParam(defaultValue = "short") String mode,
                                                   @PathVariable int id) throws NotFoundException {
        Optional<Language> language = languageService.getOne(id);
        if (language.isEmpty()) throw new NotFoundException();
        List<FilmDetailsDTO> filmDetailsList = language.get().getFilms().stream().map(FilmDetailsDTO::from).toList();
        return PaginationConverter.paginateList(pageable, filmDetailsList);
    }
}
