package com.springsakila.inventory.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springsakila.inventory.domain.core.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the film database table.
 */
@Entity
@Table(name = "film")
@NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
public class Film extends EntityBase<Film> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", unique = true, nullable = false)
    private int filmId;
    @Lob
    private String description;
    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    private Timestamp lastUpdate;
    @Positive
    private Integer length;
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @Min(1895)
    @Column(name = "release_year")
    private Short releaseYear;
    @Positive
    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;
    @Positive
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2)
    @Column(name = "rental_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal rentalRate;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    @Column(name = "replacement_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal replacementCost;
    @NotBlank
    @Size(max = 128)
    @Column(nullable = false, length = 128)
    private String title;
    //bidirectional many-to-one association to Language
    @ManyToOne
    @JoinColumn(name = "language_id")
    @NotNull
    private Language language;
    //bidirectional many-to-one association to Language
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language languageVO;
    //bidirectional many-to-one association to FilmCharacter
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FilmCharacter> filmCharacters = new ArrayList<>();
    //bidirectional many-to-one association to FilmCategory
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FilmCategory> filmCategories = new ArrayList<>();

    public Film() {
    }

    protected Film(int filmId) {
        this.filmId = filmId;
    }

    public Film(int filmId, @NotBlank @Size(max = 128) String title, String description, @Min(1895) Short releaseYear,
                @NotNull Language language,
                Language languageVO,
                @Positive Byte rentalDuration,
                @Positive @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 2, fraction = 2) BigDecimal rentalRate,
                @Positive Integer length,
                @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal replacementCost,
                Rating rating
    ) {
        super();
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.languageVO = languageVO;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
    }

    public Film(@NotBlank @Size(max = 128) String title,
                @NotNull Language language,
                @Positive Byte rentalDuration,
                @Positive @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 2, fraction = 2) BigDecimal rentalRate,
                @Positive int length,
                @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal replacementCost) {
        super();
        this.title = title;
        this.language = language;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
    }

    public int getFilmId() {
        return this.filmId;
    }

    protected void setFilmId(int filmId) {
        this.filmId = filmId;
        if (filmCharacters != null && filmCharacters.size() > 0)
            filmCharacters.forEach(item -> {
                if (item.getId().getFilmId() != filmId) item.getId().setFilmId(filmId);
            });
        if (filmCategories != null && filmCategories.size() > 0)
            filmCategories.forEach(item -> {
                if (item.getId().getFilmId() != filmId) item.getId().setFilmId(filmId);
            });
    }

    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    protected void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getLength() {
        return this.length;
    }

    protected void setLength(Integer length) {
        this.length = length;
    }

    public Rating getRating() {
        return this.rating;
    }

    private void setRating(Rating rating) {
        this.rating = rating;
    }

    public Short getReleaseYear() {
        return this.releaseYear;
    }

    protected void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Byte getRentalDuration() {
        return this.rentalDuration;
    }

    protected void setRentalDuration(Byte rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return this.rentalRate;
    }

    protected void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public BigDecimal getReplacementCost() {
        return this.replacementCost;
    }

    protected void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return this.language;
    }

    protected void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguageVO() {
        return this.languageVO;
    }

    protected void setLanguageVO(Language languageVO) {
        this.languageVO = languageVO;
    }

    public List<Character> getCharacters() {
        return this.filmCharacters.stream().map(FilmCharacter::getCharacter).toList();
    }

    protected void setCharacters(List<Character> source) {
        if (filmCharacters == null || !filmCharacters.isEmpty()) clearCharacters();
        source.forEach(this::addCharacter);
    }

    // Characters manage

    public void clearCharacters() {
        filmCharacters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        FilmCharacter filmCharacter = new FilmCharacter(this, character);
        filmCharacters.add(filmCharacter);
    }

    public void addCharacter(int characterId) {
        addCharacter(new Character(characterId));
    }

    public void removeCharacter(Character character) {
        var filmCharacter = filmCharacters.stream().filter(item -> item.getCharacter().equals(character)).findFirst();
        if (filmCharacter.isEmpty())
            return;
        filmCharacters.remove(filmCharacter.get());
    }

    public List<Category> getCategories() {
        return this.filmCategories.stream().map(FilmCategory::getCategory).toList();
    }

    protected void setCategories(List<Category> source) {
        if (filmCategories == null || !filmCategories.isEmpty()) clearCategories();
        source.forEach(this::addCategory);
    }

    // Categories manage

    public void clearCategories() {
        filmCategories = new ArrayList<>();
    }

    public void addCategory(Category item) {
        FilmCategory filmCategory = new FilmCategory(this, item);
        filmCategories.add(filmCategory);
    }

    public void addCategory(int id) {
        addCategory(new Category(id));
    }

    public void removeCategory(Category ele) {
        var filmCategory = filmCategories.stream().filter(item -> item.getCategory().equals(ele)).findFirst();
        if (filmCategory.isEmpty())
            return;
        filmCategories.remove(filmCategory.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        return filmId == other.filmId;
    }

    public Film merge(Film target) {
        target.title = title;
        target.description = description;
        target.releaseYear = releaseYear;
        target.language = language;
        target.languageVO = languageVO;
        target.rentalDuration = rentalDuration;
        target.rentalRate = rentalRate;
        target.length = length;
        target.replacementCost = replacementCost;
        target.rating = rating;
        // Remove characters are not need
        target.getCharacters().stream()
                .filter(item -> !getCharacters().contains(item))
                .forEach(target::removeCharacter);
        // Add missing characters
        getCharacters().stream()
                .filter(item -> !target.getCharacters().contains(item))
                .forEach(target::addCharacter);
        // Add missing categories
        target.getCategories().stream()
                .filter(item -> !getCategories().contains(item))
                .forEach(target::removeCategory);
        // Remove categories are not need
        getCategories().stream()
                .filter(item -> !target.getCategories().contains(item))
                .forEach(target::addCategory);
        return target;
    }
}