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
    public List<CategoryDto> getAllCategoriesPublic(@RequestParam(name = "from", defaultValue = "0", required = false) int from,
                                                    @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return service.getAllCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getByIdPublic(@PathVariable(name = "catId") long catId) {
        return service.getCategoryById(catId);
    }
}