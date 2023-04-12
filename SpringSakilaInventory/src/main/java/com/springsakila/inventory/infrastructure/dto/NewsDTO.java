package com.springsakila.inventory.infrastructure.dto;

import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    private List<FilmDTO> films;
    private List<CharacterDTO> characters;
    private List<Category> categories;
    private List<Language> languages;
}
