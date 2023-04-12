package com.springsakila.inventory.domain.core.contracts.services;

import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface DomainService<E, K> {
    List<E> getAll();

    Optional<E> getOne(K id);

    E add(E item) throws DuplicateKeyException, InvalidDataException;

    E modify(E item) throws NotFoundException, InvalidDataException;

    void delete(E item) throws InvalidDataException;

    void deleteById(K id);
}
