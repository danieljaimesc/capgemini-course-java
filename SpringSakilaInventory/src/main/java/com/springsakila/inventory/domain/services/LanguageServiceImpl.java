package com.springsakila.inventory.domain.services;

import com.springsakila.inventory.domain.contracts.repositories.LanguageRepository;
import com.springsakila.inventory.domain.contracts.services.LanguageService;
import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageRepository dao;

    @Override
    public List<Language> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Language> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getLanguageId())) throw new DuplicateKeyException(item.getErrorsMessage());
        return dao.save(item);
    }

    @Override
    public Language modify(Language item) throws NotFoundException, InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid()) throw new InvalidDataException(item.getErrorsMessage());
        if (dao.existsById(item.getLanguageId())) throw new NotFoundException();
        return dao.save(item);
    }

    @Override
    public void delete(Language item) throws InvalidDataException {
        if (item == null) throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        deleteById(item.getLanguageId());
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Language> news(Timestamp date) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
    }
}
