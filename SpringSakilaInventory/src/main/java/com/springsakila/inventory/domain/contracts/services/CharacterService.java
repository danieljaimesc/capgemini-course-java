package com.springsakila.inventory.domain.contracts.services;


import com.springsakila.inventory.domain.core.contracts.services.BaseService;
import com.springsakila.inventory.domain.core.contracts.services.ProjectionDomainService;
import com.springsakila.inventory.domain.entities.Character;

public interface CharacterService extends ProjectionDomainService<Character, Integer>, BaseService<Character,
        Integer> {

}
