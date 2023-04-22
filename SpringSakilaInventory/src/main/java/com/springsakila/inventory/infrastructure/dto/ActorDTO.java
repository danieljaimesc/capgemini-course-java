package com.springsakila.inventory.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springsakila.inventory.domain.entities.Actor;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
//TODO swagger schema
public class ActorDTO {
    @JsonProperty("id")
    private int actorId;
    @Pattern(regexp = "[A-Z]+", message = "It has to be in upper case")
    @Size(max = 45, min = 2)
    @JsonProperty("firstName")
    private String firstName;
    @Size(max = 45, min = 2)
    @Pattern(regexp = "[A-Z]+", message = "It has to be in upper case")
    @JsonProperty("lastName")
    private String lastName;

    public static ActorDTO from(Actor target) {
        return new ActorDTO(target.getActorId(), target.getFirstName(), target.getLastName());
    }

    public static Actor from(int characterId, ActorDTO target) {
        return new Actor(characterId, target.getFirstName(), target.getLastName());
    }

    public static Actor from(ActorDTO target) {
        return new Actor(target.getActorId(), target.getFirstName(), target.getLastName());
    }

}
