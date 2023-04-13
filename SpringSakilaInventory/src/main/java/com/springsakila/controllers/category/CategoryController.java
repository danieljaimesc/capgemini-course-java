package com.springsakila.controllers.category;

import com.springsakila.inventory.domain.contracts.services.CategoryService;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.shared.exceptions.BadRequestException;
import com.springsakila.inventory.shared.exceptions.DuplicateKeyException;
import com.springsakila.inventory.shared.exceptions.InvalidDataException;
import com.springsakila.inventory.shared.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
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

}
