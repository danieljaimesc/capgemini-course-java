package com.springsakila.inventory.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

/**
 * The primary key class for the film_actor database table.
 */
@Embeddable
public class FilmCharacterPK implements Serializable {
    //default serial version id, required for serializable classes.
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "actor_id", insertable = false, updatable = false, unique = true, nullable = false)
    private int characterId;

    @Column(name = "film_id", insertable = false, updatable = false, unique = true, nullable = false)
    private int filmId;

    public FilmCharacterPK() {
    }

    public int getCharacterId() {
        return this.characterId;
    }

    protected void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getFilmId() {
        return this.filmId;
    }

    protected void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilmCharacterPK castOther)) {
            return false;
        }
        return
                (this.characterId == castOther.characterId)
                        && (this.filmId == castOther.filmId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.characterId;
        hash = hash * prime + this.filmId;

        return hash;
    }
}