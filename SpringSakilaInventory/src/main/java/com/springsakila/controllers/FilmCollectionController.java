package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Film;
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
        List<Film> filmsList = sort != null ? (List<Film>) filmService.getByProjection(Sort.by(sort), Film.class) :
                filmService.getByProjection(Film.class);
        return filmsList.stream().map(FilmDTO::from).toList();
    }

    @GetMapping(params = "page")
    public Page<FilmDTO> getAll(Pageable pageable) {
        return filmService.getByProjection(pageable, FilmDTO.class);
    }
}
