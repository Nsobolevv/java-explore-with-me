package ru.practicum.main.requests.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.requests.dto.ParticipationRequestDto;
import ru.practicum.main.requests.model.ParticipationRequest;


@UtilityClass
public class ParticipationRequestMapper {
    public static ParticipationRequestDto toDto(ParticipationRequest request) {
        return ParticipationRequestDto.builder()
                .created(request.getCreated())
                .event(request.getEvent().getId())
                .id(request.getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus())
                .build();
    }
}