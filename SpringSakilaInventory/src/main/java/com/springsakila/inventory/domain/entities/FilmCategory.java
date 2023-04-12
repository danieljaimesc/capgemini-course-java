package com.springsakila.inventory.domain.entities;

import com.springsakila.inventory.domain.core.entities.EntityBase;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the film_category database table.
 */
@Entity
@Table(name = "film_category")
@NamedQuery(name = "FilmCategory.findAll", query = "SELECT f FROM FilmCategory f")
public class FilmCategory extends EntityBase<FilmCategory> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FilmCategoryPK id;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    //bidirectional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;

    //bidirectional many-to-one association to Film
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
    private Film film;

    protected FilmCategory() {
    }

    public FilmCategory(Film film, Category category) {
        super();
        this.film = film;
        this.category = category;
    }

    public FilmCategoryPK getId() {
        return this.id;
    }

    protected void setId(FilmCategoryPK id) {
        this.id = id;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    protected void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Category getCategory() {
        return this.category;
    }

    protected void setCategory(Category category) {
        this.category = category;
    }

    public Film getFilm() {
        return this.film;
    }

    protected void setFilm(Film film) {
        this.film = film;
    }
}