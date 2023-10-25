package ru.practicum.main.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.constants.Pattern;
import ru.practicum.main.events.dto.EventFullDto;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.events.model.enums.SortEventsBy;
import ru.practicum.main.events.service.EventService;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventController {
    private final EventService service;

    @GetMapping
    public List<EventShortDto> getEventsPublic(@RequestParam(required = false) String text,
                                               @RequestParam(required = false) List<Long> categories,
                                               @RequestParam(required = false) Boolean paid,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = Pattern.DATE) LocalDateTime rangeStart,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = Pattern.DATE) LocalDateTime rangeEnd,
                                               @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                               @RequestParam(required = false) SortEventsBy sort,
                                               @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size,
                                               HttpServletRequest request) {
        return service.getEventsPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByIdPublic(@PathVariable long eventId,
                                           HttpServletRequest request) {
        return service.getEventByIdPublic(eventId, request);
    }
}