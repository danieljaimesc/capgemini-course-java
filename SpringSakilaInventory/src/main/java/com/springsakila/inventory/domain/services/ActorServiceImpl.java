package com.springsakila.inventory.domain.services;

import com.springsakila.inventory.domain.contracts.repositories.ActorRepository;
import com.springsakila.inventory.domain.contracts.services.ActorService;
import com.springsakila.inventory.domain.entities.Actor;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
class ActorServiceImpl implements ActorService {
    @Autowired
    ActorRepository dao;

    @Override
    public Iterable<Actor> getAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public Page<Actor> getAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public <T> List<T> getByProjection(Class<T> type) {
        return dao.findAllBy(type);
    }

    @Override
    public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
        return dao.findAllBy(sort, type);
    }

    @Override
    public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
        return dao.findAllBy(pageable, type);
    }

    @Override
    public List<Actor> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Actor> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getActorId())) throw new DuplicateKeyException(item.getErrorsMessage());
        return dao.save(item);
    }

    @Override
    public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        var character = dao.findById(item.getActorId());
        if (character.isEmpty()) throw new NotFoundException();
        return dao.save(item.merge(character.get()));
    }

    @Override
    public void delete(Actor item) throws InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        deleteById(item.getActorId());
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Actor> news(Timestamp date) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
    }
}
