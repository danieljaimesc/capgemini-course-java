package com.springsakila.inventory.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.Character;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
//TODO swagger schema
public class CharacterDTO {
    @JsonProperty("id")
    private int characterId;
    @Pattern(regexp = "[A-Z]+", message = "It has to be in upper case")
    @Size(max = 45, min = 2)
    @JsonProperty("firstName")
    private String firstName;
    @Size(max = 45, min = 2)
    @Pattern(regexp = "[A-Z]+", message = "It has to be in upper case")
    @JsonProperty("lastName")
    private String lastName;

    public static CharacterDTO from(Character target) {
        return new CharacterDTO(target.getCharacterId(), target.getFirstName(), target.getLastName());
    }

    public static Character from(int characterId, CharacterDTO target) {
        return new Character(characterId, target.getFirstName(), target.getLastName());
    }

    public static Character from(CharacterDTO target) {
        return new Character(target.getCharacterId(), target.getFirstName(), target.getLastName());
    }

}
