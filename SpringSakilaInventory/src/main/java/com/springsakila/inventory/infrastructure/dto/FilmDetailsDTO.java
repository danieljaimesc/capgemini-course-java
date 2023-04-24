package com.springsakila.inventory.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.Film;
import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.domain.entities.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO swagger schema
public class FilmDetailsDTO {
    @JsonProperty("id")
    private int filmId;
    private String description;
    private int length;
    @Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$")
    private String rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private Short releaseYear;
    private Byte rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCost;
    @NotBlank
    @Size(min = 2, max = 128)
    private String title;
    @NotNull
    private Language language;
    private Language languageVO;
    private List<Object> actorList = new ArrayList<>();
    private List<Object> categoryList = new ArrayList<>();

    public static FilmDetailsDTO from(Film source) {
        return new FilmDetailsDTO(
                source.getFilmId(),
                source.getDescription(),
                source.getLength(),
                source.getRating() == null ? null : source.getRating().getValue(),
                source.getReleaseYear(),
                source.getRentalDuration(),
                source.getRentalRate(),
                source.getReplacementCost(),
                source.getTitle(),
                source.getLanguage() == null ? null : source.getLanguage(),
                source.getLanguageVO() == null ? null : source.getLanguageVO(),
                new ArrayList<>(source.getActors().stream().map(ActorDTO::from).toList()),
                new ArrayList<>(source.getCategories())
        );
    }

    public static Film from(FilmDetailsDTO source) {
        Film result = new Film(
                source.getFilmId(),
                source.getTitle(),
                source.getDescription(),
                source.getReleaseYear(),
                source.getLanguage() == null ? null : source.getLanguage(),
                source.getLanguageVO() == null ? null : source.getLanguageVO(),
                source.getRentalDuration(),
                source.getRentalRate(),
                source.getLength(),
                source.getReplacementCost(),
                source.getRating() == null ? null : Rating.getEnum(source.getRating())
        );
        source.getActorList().forEach(item -> result.addActor((Integer) item));
        source.getCategoryList().forEach(item -> result.addCategory((Integer) item));
        return result;
    }

}
