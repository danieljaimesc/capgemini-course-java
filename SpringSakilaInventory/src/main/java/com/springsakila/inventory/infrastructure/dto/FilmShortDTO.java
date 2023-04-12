package com.springsakila.inventory.infrastructure.dto;


import com.springsakila.inventory.domain.entities.Film;
import lombok.Value;

@Value
public class FilmShortDTO {
    private int filmId;
    private String title;

    public static FilmShortDTO from(Film source) {
        return new FilmShortDTO(source.getFilmId(), source.getTitle());
    }
}
