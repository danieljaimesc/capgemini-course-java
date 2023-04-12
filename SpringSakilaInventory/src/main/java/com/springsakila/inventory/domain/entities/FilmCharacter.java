package com.springsakila.inventory.domain.entities;

import com.springsakila.inventory.domain.core.entities.EntityBase;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the film_actor database table.
 */
@Entity
@Table(name = "film_actor")
@NamedQuery(name = "FilmCharacter.findAll", query = "SELECT f FROM FilmCharacter f")
public class FilmCharacter extends EntityBase<FilmCharacter> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FilmCharacterPK id;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    //bidirectional many-to-one association to Character
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false, insertable = false, updatable = false)
    private Character character;

    //bidirectional many-to-one association to Film
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
    private Film film;

    public FilmCharacter() {
    }

    public FilmCharacter(Film film, Character character) {
        super();
        this.character = character;
        this.film = film;
    }

    public FilmCharacterPK getId() {
        return this.id;
    }

    protected void setId(FilmCharacterPK id) {
        this.id = id;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    protected void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Character getCharacter() {
        return this.character;
    }

    protected void setCharacter(Character character) {
        this.character = character;
    }

    public Film getFilm() {
        return this.film;
    }

    protected void setFilm(Film film) {
        this.film = film;
    }

}