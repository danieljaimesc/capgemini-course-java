package com.springsakila.inventory.domain.contracts.services;


import com.springsakila.inventory.domain.core.contracts.services.BaseService;
import com.springsakila.inventory.domain.core.contracts.services.ProjectionDomainService;
import com.springsakila.inventory.domain.entities.Actor;

public interface ActorService extends ProjectionDomainService<Actor, Integer>, BaseService<Actor,
        Integer> {

}
