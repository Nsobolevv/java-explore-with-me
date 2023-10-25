package ru.practicum.main.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main.categories.dto.CategoryDto;
import ru.practicum.main.constants.Pattern;
import ru.practicum.main.users.dto.UserShortDto;


import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventShortDto {
    String annotation;
    CategoryDto category;
    int confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Pattern.DATE)
    LocalDateTime eventDate;
    long id;
    UserShortDto initiator;
    boolean paid;
    String title;
    int views;
}