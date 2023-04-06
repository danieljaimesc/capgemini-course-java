package com.example.ddd.domain.contracts;

import com.example.ddd.domain.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Override
    Actor save(Actor entity);

    @Override
    <S extends Actor> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Actor> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    List<Actor> findAll();

    @Override
    List<Actor> findAllById(Iterable<Integer> integers);

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
