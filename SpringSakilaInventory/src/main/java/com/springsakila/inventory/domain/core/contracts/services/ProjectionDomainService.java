package com.springsakila.inventory.domain.core.contracts.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProjectionDomainService<E, K> extends PagingAndSortingDomainService<E, K> {
    <T> List<T> getByProjection(Class<T> type);

    <T> Iterable<T> getByProjection(Sort sort, Class<T> type);

    <T> Page<T> getByProjection(Pageable pageable, Class<T> type);
}
