package com.example.ddd.infrastructure;

import com.example.ddd.domain.contracts.ActorRepository;
import com.example.ddd.domain.entities.Actor;

import java.util.Optional;

public class SakilaMysqlRepository implements ActorRepository {

    @Override
    public Actor save(Actor entity) {
        return null;
    }

    @Override
    public <S extends Actor> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Actor> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Actor> findAll() {
        return null;
    }

    @Override
    public Iterable<Actor> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Actor entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Actor> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
