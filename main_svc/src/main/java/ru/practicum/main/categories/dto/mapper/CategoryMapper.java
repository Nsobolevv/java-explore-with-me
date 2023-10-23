package ru.practicum.main.categories.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.categories.model.Category;


@UtilityClass
public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toCategory(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}