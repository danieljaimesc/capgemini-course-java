package com.springsakila.controllers;

import com.springsakila.inventory.application.CatalogService;
import com.springsakila.inventory.infrastructure.dto.NewsDTO;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/api/v1/catalog")
    public NewsDTO get(@Parameter(example = "2021-01-01 00:00:00") @RequestParam(required = false, defaultValue =
            "2006-01-01 00:00:00") Timestamp date) {
        return catalogService.news(date);
    }

    @GetMapping(path = "/")
    public ResponseEntity<CatalogResources> getResources() {
        return ResponseEntity.ok().header("Content-Type", "application/hal+json").body(new CatalogResources());
    }

    @Value
    public static class CatalogResources {
        private CatalogoLinks _links = new CatalogoLinks();

        @Value
        public class CatalogoLinks {
            private Href self = new Href("");
            private HashMap<String, Href> character = new HashMap<>() {{
                put("One", new Href("/api/v1/character"));
                put("Collection", new Href("/api/v1/characters"));
            }};
            private Map<String, Href> film = new HashMap<>() {{
                put("One", new Href("/api/v1/film"));
                put("Collection", new Href("/api/v1/films"));
            }};
            private HashMap<String, Href> category = new HashMap<>() {{
                put("One", new Href("/api/v1/category"));
                put("Collection", new Href("/api/v1/categories"));
            }};
            private HashMap<String, Href> languages = new HashMap<>() {{
                put("One", new Href("/api/v1/language"));
                put("Collection", new Href("/api/v1/languages"));
            }};
            private Href news = new Href("/api/v1/news");
            private Href documentation = new Href("/open-api");

            public class Href {
                private final String href;

                public Href(String path) {
                    href = ServletUriComponentsBuilder.fromCurrentRequest().path(path).toUriString();
                }

                public String getHref() {
                    return href;
                }
            }
        }
    }
}
