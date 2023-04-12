package com.springsakila.inventory.domain.core.contracts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RepositoryWithProjections {
    <T> List<T> findAllBy(Class<T> type);

    <T> Iterable<T> findAllBy(Sort order, Class<T> type);

    <T> Page<T> findAllBy(Pageable page, Class<T> type);
}
