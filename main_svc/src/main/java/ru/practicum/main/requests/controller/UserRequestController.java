package ru.practicum.main.requests.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.requests.dto.ParticipationRequestDto;
import ru.practicum.main.requests.service.RequestService;


import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class UserRequestController {
    private final RequestService service;

    @GetMapping
    public List<ParticipationRequestDto> getByUserId(@PathVariable long userId) {
        return service.getRequestByUserIdByUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto add(@PathVariable long userId,
                                       @RequestParam long eventId) {
        return service.addRequestByUser(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable long userId,
                                          @PathVariable long requestId) {
        return service.cancelRequest(userId, requestId);
    }
}