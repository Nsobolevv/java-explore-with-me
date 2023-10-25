package ru.practicum.main.categories.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.service.CategoryService;


import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class PublicCategoryController {
    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAllCategoriesPublic(@RequestParam(defaultValue = "0") int from,
                                                    @RequestParam(defaultValue = "10") int size) {
        return service.getAllCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getByIdPublic(@PathVariable long catId) {
        return service.getCategoryById(catId);
    }
}