package com.example.ddd.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorPK implements Serializable {
    @Column(name = "actor_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;
    @Column(name = "film_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }
}
