package com.springsakila.controllers;

import com.springsakila.inventory.domain.contracts.services.CategoryService;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.PaginationConverter;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public Category get(@PathVariable int id) throws NotFoundException {
        var category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        return category.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category postCreate(@Valid @RequestBody Category category) throws InvalidDataException,
            DuplicateKeyException {
        return categoryService.add(category);
    }

    @PatchMapping("/{id}")
    public Category update(@PathVariable int id, @Valid @RequestBody Category category) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != category.getCategoryId()) throw new BadRequestException("Identifiers do not match");
        return categoryService.modify(category);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

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
