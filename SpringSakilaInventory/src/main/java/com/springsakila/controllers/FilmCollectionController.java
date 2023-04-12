package com.springsakila.controllers;

import com.springsakila.inventory.domain.services.FilmServiceImpl;
import com.springsakila.inventory.infrastructure.dto.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmCollectionController {
    @Autowired
    private FilmServiceImpl filmService;

    @GetMapping
    public List<FilmDTO> getAll(@RequestParam(required = false) String sort) {
        return sort != null ? (List<FilmDTO>) filmService.getByProjection(Sort.by(sort),
                FilmDTO.class) : filmService.getByProjection(FilmDTO.class);
    }

    @GetMapping(params = "page")
    public Page<FilmDTO> getAll(Pageable pageable) {
        return filmService.getByProjection(pageable, FilmDTO.class);
    }
}
