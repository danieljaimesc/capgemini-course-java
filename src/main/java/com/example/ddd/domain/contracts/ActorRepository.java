package com.example.ddd.domain.contracts;

import com.example.ddd.domain.entities.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Integer> {

    @Override
    Actor save(Actor entity);

    @Override
    <S extends Actor> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Actor> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Actor> findAll();

    @Override
    Iterable<Actor> findAllById(Iterable<Integer> integers);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Actor entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Actor> entities);

    @Override
    void deleteAll();
}
