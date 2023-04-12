package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.domain.services.LanguageServiceImpl;
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
    public Language postCreate(@RequestBody Language language) throws InvalidDataException, BadRequestException,
            NotFoundException, DuplicateKeyException {
        Language languageResult;
        if (languageService.getOne(language.getLanguageId()).isPresent())
            languageResult = update(language.getLanguageId(), language);
        else languageResult = languageService.add(language);
        return languageResult;
    }

    @PatchMapping("/language/{id}")
    public Language update(@PathVariable int id, @RequestBody Language language) throws BadRequestException,
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
    public List<FilmDTO> getFilmList(@PathVariable int id) throws NotFoundException {
        Optional<Language> language = languageService.getOne(id);
        if (language.isEmpty()) throw new NotFoundException();
        return language.get().getFilms().stream().map(FilmDTO::from).toList();
    }
}
