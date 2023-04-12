package com.springsakila.inventory.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.Character;
import lombok.Value;

@Value
public class CharacterDTO {
    @JsonProperty("id")
    private int characterId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;

    public static CharacterDTO from(Character target) {
        return new CharacterDTO(target.getCharacterId(), target.getFirstName(), target.getLastName());
    }

    public static Character from(CharacterDTO target) {
        return new Character(target.getCharacterId(), target.getFirstName(), target.getLastName());
    }

}
