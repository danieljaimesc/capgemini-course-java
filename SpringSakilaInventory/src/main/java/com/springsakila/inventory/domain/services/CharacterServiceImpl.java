package com.springsakila.inventory.domain.services;

import com.springsakila.inventory.domain.contracts.repositories.CharacterRepository;
import com.springsakila.inventory.domain.contracts.services.CharacterService;
import com.springsakila.inventory.domain.entities.Character;
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
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository dao;

    @Override
    public Iterable<Character> getAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public Page<Character> getAll(Pageable pageable) {
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
    public List<Character> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Character> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Character add(Character item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getCharacterId())) throw new DuplicateKeyException(item.getErrorsMessage());

        return dao.save(item);
    }

    @Override
    public Character modify(Character item) throws NotFoundException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (!dao.existsById(item.getCharacterId())) throw new NotFoundException();
        return dao.save(item);
    }

    @Override
    public void delete(Character item) throws InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        deleteById(item.getCharacterId());
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Character> news(Timestamp date) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
    }
}
