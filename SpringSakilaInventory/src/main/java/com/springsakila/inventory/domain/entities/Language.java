package com.springsakila.inventory.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springsakila.inventory.domain.core.entities.EntityBase;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the language database table.
 */
@Entity
@Table(name = "language")
@NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
public class Language extends EntityBase<Language> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", unique = true, nullable = false)
    private int languageId;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private Timestamp lastUpdate;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    //bidirectional many-to-one association to Film
    @OneToMany(mappedBy = "language")
    @JsonIgnore
    private List<Film> films;

    //bidirectional many-to-one association to Film
    @OneToMany(mappedBy = "languageVO")
    @JsonIgnore
    private List<Film> filmsVO;

    public Language() {
    }

    public Language(Integer Id) {
        super();
        this.languageId = Id;
    }

    public int getLanguageId() {
        return this.languageId;
    }

    protected void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    protected void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return this.films;
    }

    protected void setFilms(List<Film> films) {
        this.films = films;
    }

    public Film addFilm(Film film) {
        getFilms().add(film);
        film.setLanguage(this);

        return film;
    }

    public Film removeFilm(Film film) {
        getFilms().remove(film);
        film.setLanguage(null);

        return film;
    }

    public List<Film> getFilmsVO() {
        return this.filmsVO;
    }

    protected void setFilmsVO(List<Film> filmsVO) {
        this.filmsVO = filmsVO;
    }

    public Film addFilmsVO(Film filmsVO) {
        getFilmsVO().add(filmsVO);
        filmsVO.setLanguageVO(this);

        return filmsVO;
    }

    public Film removeFilmsVO(Film filmsVO) {
        getFilmsVO().remove(filmsVO);
        filmsVO.setLanguageVO(null);

        return filmsVO;
    }

}