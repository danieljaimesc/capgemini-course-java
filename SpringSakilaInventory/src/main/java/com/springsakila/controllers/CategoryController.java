package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.services.CategoryServiceImpl;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.PaginationConverter;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/category/{id}")
    public Category get(@PathVariable int id) throws NotFoundException {
        var category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        return category.get();
    }

    @PostMapping("/category")
    public Category postCreate(@Valid @RequestBody Category category) throws InvalidDataException,
            DuplicateKeyException,
            BadRequestException, NotFoundException {
        Category categoryResult;
        if (categoryService.getOne(category.getCategoryId()).isPresent())
            categoryResult = update(category.getCategoryId(), category);
        else categoryResult = categoryService.add(category);
        return categoryResult;
    }

    @PatchMapping("/category/{id}")
    public Category update(@PathVariable int id, @Valid @RequestBody Category category) throws BadRequestException,
            InvalidDataException, NotFoundException {
        if (id != category.getCategoryId()) throw new BadRequestException("Identifiers do not match");
        return categoryService.modify(category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}/films")
    public Page<FilmShortDTO> getFilmShortList(Pageable pageable, @RequestParam(defaultValue = "short") String mode,
                                               @PathVariable int id) throws NotFoundException {
        Optional<Category> category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        List<FilmShortDTO> filmShortList =
                category.get().getFilmCategories().stream().map(item -> FilmShortDTO.from(item.getFilm())).toList();
        return PaginationConverter.paginateList(pageable, filmShortList);
    }

    @GetMapping(path = "/category/{id}/films", params = "mode=details")
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
