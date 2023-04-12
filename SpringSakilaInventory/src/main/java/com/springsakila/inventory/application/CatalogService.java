package com.springsakila.inventory.application;

import com.springsakila.inventory.domain.contracts.services.CategoryService;
import com.springsakila.inventory.domain.contracts.services.CharacterService;
import com.springsakila.inventory.domain.contracts.services.FilmService;
import com.springsakila.inventory.domain.contracts.services.LanguageService;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmDTO;
import com.springsakila.inventory.infrastructure.dto.NewsDTO;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Service
public class CatalogService {
    @Autowired
    private FilmService filmSrv;
    @Autowired
    private CharacterService characterSrv;
    @Autowired
    private CategoryService categorySrv;
    @Autowired
    private LanguageService languageSrv;

    public NewsDTO news(@Null Timestamp date) {
        // Timestamp valid format: "2019-01-01 00:00:00"
        if (date == null) date = Timestamp.from(ZonedDateTime.now().minusYears(20).toInstant());
        return new NewsDTO(
                filmSrv.news(date).stream().map(FilmDTO::from).toList(),
                characterSrv.news(date).stream().map(CharacterDTO::from).toList(),
                categorySrv.news(date),
                languageSrv.news(date));
    }

}
