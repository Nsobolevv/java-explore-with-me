package ru.practicum.main.events.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.categories.dto.mapper.CategoryMapper;
import ru.practicum.main.events.dto.EventFullDto;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.location.dto.mapper.LocationMapper;
import ru.practicum.main.users.dto.mapper.UserMapper;


@UtilityClass
public class EventMapper {
    public static EventShortDto toShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests().size())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.toShortDto(event.getInitiator()))
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static EventFullDto toFullDto(Event event) {
        EventFullDto dto = EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toDto(event.getCategory()))
                .createdOn(event.getCreated())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.toShortDto(event.getInitiator()))
                .location(LocationMapper.toDto(event.getLocation()))
                .paid(event.isPaid())
                .participantLimit(event.getParticipantLimit())
                .requestModeration(event.isRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
        if (event.getConfirmedRequests() != null) {
            dto.setConfirmedRequests(event.getConfirmedRequests().size());
        } else {
            dto.setConfirmedRequests(0);
        }
        return dto;
    }
}