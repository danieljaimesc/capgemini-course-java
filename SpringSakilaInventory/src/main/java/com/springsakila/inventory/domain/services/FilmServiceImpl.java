package com.springsakila.inventory.domain.services;

import com.springsakila.inventory.domain.contracts.repositories.FilmRepository;
import com.springsakila.inventory.domain.contracts.services.FilmService;
import com.springsakila.inventory.domain.entities.Film;
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
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository dao;

    @Override
    public List<Film> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Film> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getFilmId())) throw new DuplicateKeyException(item.getErrorsMessage());
        return dao.save(item);
    }

    @Override
    public Film modify(Film item) throws NotFoundException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getFilmId())) throw new NotFoundException();
        return dao.save(item);
    }

    @Override
    public void delete(Film item) throws InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        deleteById(item.getFilmId());
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Film> news(Timestamp date) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
    }

    @Override
    public Iterable<Film> getAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public Page<Film> getAll(Pageable pageable) {
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
}
