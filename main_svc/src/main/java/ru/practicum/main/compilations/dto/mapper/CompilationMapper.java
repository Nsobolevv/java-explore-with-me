package ru.practicum.main.compilations.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.model.Compilation;
import ru.practicum.main.events.dto.mapper.EventMapper;


import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {
    public static CompilationDto toDto(Compilation compilation) {
        return CompilationDto.builder()
                .events(compilation.getEvents().stream()
                        .map(EventMapper::toShortDto)
                        .collect(Collectors.toList()))
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}