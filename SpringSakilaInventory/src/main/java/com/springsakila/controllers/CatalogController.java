package com.springsakila.controllers;

import com.springsakila.inventory.application.CatalogService;
import com.springsakila.inventory.infrastructure.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping
    public NewsDTO get(@RequestParam(required = false) LocalDate date) {
        Timestamp timestamp = null;
        if (date != null) {
            timestamp = Timestamp.valueOf(date.atStartOfDay());
        }
        return catalogService.news(timestamp);
    }
}
