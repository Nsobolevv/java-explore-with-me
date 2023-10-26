package ru.practicum.main.location.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.location.dto.LocationDto;
import ru.practicum.main.location.model.Location;


@UtilityClass
public class LocationMapper {
    public static LocationDto toDto(Location location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}