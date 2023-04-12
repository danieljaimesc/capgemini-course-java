package com.springsakila.inventory.infrastructure.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.Film;
import lombok.Value;

@Value
//TODO swagger schema
public class FilmShortDTO {
    @JsonProperty("id")
    private int filmId;
    private String title;

    public static FilmShortDTO from(Film source) {
        return new FilmShortDTO(source.getFilmId(), source.getTitle());
    }
}
