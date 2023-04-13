package com.springsakila.controllers.category;

import com.springsakila.inventory.domain.contracts.services.CategoryService;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.PaginationConverter;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryFilmCollectionController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}/films")
    @Transactional
    public Page<FilmShortDTO> getFilmShortList(Pageable pageable, @RequestParam(defaultValue = "short") String mode,
                                               @PathVariable int id) throws NotFoundException {
        Optional<Category> category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        List<FilmShortDTO> filmShortList =
                category.get().getFilmCategories().stream().map(item -> FilmShortDTO.from(item.getFilm())).toList();
        return PaginationConverter.paginateList(pageable, filmShortList);
    }

    @GetMapping(path = "/{id}/films", params = "mode=details")
    @Transactional
    public Page<FilmDetailsDTO> getFilmDetailsList(@Parameter(hidden = true) Pageable pageable,
                                                   @RequestParam(defaultValue = "short") String mode,
                                                   @PathVariable int id) throws NotFoundException {
        Optional<Category> category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        List<FilmDetailsDTO> filmDetailsList =
                category.get().getFilmCategories().stream().map(item -> FilmDetailsDTO.from(item.getFilm())).toList();
        return PaginationConverter.paginateList(pageable, filmDetailsList);
    }

}
