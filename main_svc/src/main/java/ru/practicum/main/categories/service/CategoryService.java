package ru.practicum.main.categories.service;



import ru.practicum.main.categories.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategoryByAdmin(CategoryDto dto);

    CategoryDto updateCategoryByAdmin(long catId, CategoryDto dto);

    void deleteCategoryByAdmin(long catId);

    List<CategoryDto> getAllCategories(int from, int size);

    CategoryDto getCategoryById(long catId);
}