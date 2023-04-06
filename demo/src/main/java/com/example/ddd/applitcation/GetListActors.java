package com.example.ddd.applitcation;

import com.example.ddd.domain.entities.Actor;
import com.example.ddd.domain.contracts.ActorRepository;

import java.util.List;

public class GetListActors {
    private ActorRepository repository;

    public GetListActors(ActorRepository repository) {
        this.repository = repository;
    }

    public List<Actor> getList() {
        return (List<Actor>) this.repository.findAll();
    }
}
