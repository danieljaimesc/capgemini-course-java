package com.springsakila.controllers.language;

import com.springsakila.inventory.domain.contracts.services.LanguageService;
import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/language")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping("/{id}")
    public Language get(@PathVariable int id) throws NotFoundException {
        var language = languageService.getOne(id);
        if (language.isEmpty()) throw new NotFoundException();
        return language.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Language postCreate(@Valid @RequestBody Language language) throws InvalidDataException, BadRequestException,
            DuplicateKeyException {
        return languageService.add(language);
    }

    @PatchMapping("/{id}")
    public Language update(@PathVariable int id, @Valid @RequestBody Language language) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != language.getLanguageId()) throw new BadRequestException("Identifiers do not match");
        return languageService.modify(language);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        languageService.deleteById(id);
    }
}
