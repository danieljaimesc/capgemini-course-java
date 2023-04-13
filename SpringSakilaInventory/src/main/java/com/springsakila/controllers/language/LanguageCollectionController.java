package com.springsakila.controllers.language;

import com.springsakila.inventory.domain.contracts.services.LanguageService;
import com.springsakila.inventory.domain.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageCollectionController {

    @Autowired
    private LanguageService languageService;

    @GetMapping()
    public List<Language> getAll() {
        return languageService.getAll();
    }
}
