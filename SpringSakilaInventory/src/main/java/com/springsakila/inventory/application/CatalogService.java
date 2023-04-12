package com.springsakila.inventory.application;

import com.springsakila.inventory.infrastructure.dto.NewsDTO;
import jakarta.validation.constraints.Null;

import java.sql.Timestamp;

public interface CatalogService {
    NewsDTO news(@Null Timestamp date);
}
