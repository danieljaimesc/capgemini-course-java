package com.springsakila.inventory.domain.entities;

import com.springsakila.inventory.domain.core.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actor")
@NamedQuery(name = "Character.findAll", query = "SELECT a FROM Character a")
public class Character extends EntityBase<Character> implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id", unique = true, nullable = false)
    private int characterId;

    @Column(name = "first_name", nullable = false, length = 45)
    @NotBlank
    @Size(max = 45, min = 2)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @Size(max = 45, min = 2)
    @Pattern(regexp = "[A-Z]+", message = "It has to be in upper case")
    private String lastName;

    @Column(name = "last_update", insertable = false, updatable = false, nullable = false)
    @PastOrPresent
    private Timestamp lastUpdate;

    //bidirectional many-to-one association to FilmCharacter
    @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
    private List<FilmCharacter> filmCharacters = new ArrayList<>();


    public Character(int characterId, String firstName, String lastName) {
        this.characterId = characterId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Character() {

    }

    public Character(int characterId) {
        this.characterId = characterId;
    }

    public int getCharacterId() {
        return characterId;
    }

    protected void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    protected void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<FilmCharacter> getFilmCharacters() {
        return this.filmCharacters;
    }

    protected void setFilmCharacters(List<FilmCharacter> filmCharacters) {
        this.filmCharacters = filmCharacters;
    }

    public FilmCharacter addFilmCharacter(FilmCharacter filmCharacter) {
        getFilmCharacters().add(filmCharacter);
        filmCharacter.setCharacter(this);
        return filmCharacter;
    }

    public FilmCharacter removeFilmCharacter(FilmCharacter filmCharacter) {
        getFilmCharacters().remove(filmCharacter);
        filmCharacter.setCharacter(null);
        return filmCharacter;
    }


    @Override
    public int hashCode() {
        return Objects.hash(characterId, firstName, lastName, lastUpdate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Character other = (Character) obj;
        return characterId == other.characterId;
    }
}
