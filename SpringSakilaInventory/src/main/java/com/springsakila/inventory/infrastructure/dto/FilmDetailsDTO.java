package com.springsakila.inventory.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.*;
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
    private List<ActorDTO> actorList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

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
                source.getActors().stream().map(ActorDTO::from).toList(),
                source.getCategories()
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
        source.getActorList().forEach((item) -> result.addActor(item.getActorId()));
        source.getCategoryList().forEach(result::addCategory);
        return result;
    }

}
