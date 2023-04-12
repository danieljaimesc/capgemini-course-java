package com.springsakila.inventory.domain.core.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.sql.Timestamp;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E, K> extends JpaRepository<E, K>, JpaSpecificationExecutor<E>,
        RepositoryWithProjections {
    List<E> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp date);

}
