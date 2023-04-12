package com.springsakila.inventory.domain.contracts.services;


import com.springsakila.inventory.domain.core.contracts.services.BaseService;
import com.springsakila.inventory.domain.core.contracts.services.ProjectionDomainService;
import com.springsakila.inventory.domain.entities.Film;

public interface FilmService extends ProjectionDomainService<Film, Integer>, BaseService<Film,
        Integer> {

}
