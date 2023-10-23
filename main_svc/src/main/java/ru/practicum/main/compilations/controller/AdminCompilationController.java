package ru.practicum.main.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequestDto;
import ru.practicum.main.compilations.service.CompilationService;


import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationController {
    private final CompilationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addByAdmin(@Valid @RequestBody NewCompilationDto dto) {
        return service.add(dto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAdmin(@PathVariable long compId) {
        service.delete(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateByAdmin(@PathVariable long compId,
                                        @Valid @RequestBody UpdateCompilationRequestDto dto) {
        return service.update(compId, dto);
    }
}