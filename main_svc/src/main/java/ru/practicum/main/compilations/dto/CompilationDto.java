package ru.practicum.main.compilations.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main.events.dto.EventShortDto;


import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompilationDto {
    List<EventShortDto> events;
    Long id;
    Boolean pinned;
    String title;
}