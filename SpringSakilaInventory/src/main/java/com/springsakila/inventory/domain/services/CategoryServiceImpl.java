package com.springsakila.inventory.domain.services;

import com.springsakila.inventory.domain.contracts.repositories.CategoryRepository;
import com.springsakila.inventory.domain.contracts.services.CategoryService;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository dao;

    @Override
    public List<Category> getAll() {
        return dao.findAll(Sort.by("name"));
    }

    @Override
    public Optional<Category> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
        //VALIDATE IS NOT NULL AND
        if (item == null)
            throw new InvalidDataException(InvalidDataException.MISSING_DATA);
        if (item.isInvalid())
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        //IF IT HAS THE SAME PK IS DUPLICATE
        if (getOne(item.getCategoryId()).isPresent())
            throw new DuplicateKeyException();
        return dao.save(item);
    }

    @Override
    public Category modify(Category item) throws NotFoundException, InvalidDataException {
        if (item == null)
            throw new InvalidDataException(InvalidDataException.CANT_BE_NULL);
        if (item.isInvalid())
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        if (dao.existsById((item.getCategoryId())))
            throw new NotFoundException();
        return dao.save(item);
    }

    @Override
    public void delete(Category item) throws InvalidDataException {
        if (item == null)
            throw new InvalidDataException("Missing data");
        deleteById(item.getCategoryId());
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Category> news(Timestamp date) {
        return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
    }
}
