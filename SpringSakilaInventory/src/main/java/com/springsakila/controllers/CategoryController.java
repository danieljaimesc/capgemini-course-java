package com.springsakila.controllers;

import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.services.CategoryServiceImpl;
import com.springsakila.inventory.infrastructure.dto.FilmDTO;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Category postCreate(@RequestBody Category category) throws InvalidDataException, DuplicateKeyException,
            BadRequestException, NotFoundException {
        Category categoryResult;
        if (categoryService.getOne(category.getCategoryId()).isPresent())
            categoryResult = update(category.getCategoryId(), category);
        else categoryResult = categoryService.add(category);
        return categoryResult;
    }

    @PatchMapping("/category/{id}")
    public Category update(@PathVariable int id, @RequestBody Category category) throws BadRequestException,
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
    public List<FilmDTO> getFilmList(@PathVariable int id) throws NotFoundException {
        Optional<Category> category = categoryService.getOne(id);
        if (category.isEmpty()) throw new NotFoundException();
        return category.get().getFilmCategories().stream().map(item -> FilmDTO.from(item.getFilm())).toList();
    }


}
