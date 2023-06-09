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
//TODO swagger schema
public class NewsDTO {
    private List<FilmShortDTO> filmList;
    private List<ActorDTO> actorList;
    private List<Category> categoryList;
    private List<Language> languageList;
}
