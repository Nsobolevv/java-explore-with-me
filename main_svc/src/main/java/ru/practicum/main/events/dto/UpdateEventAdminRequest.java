package ru.practicum.main.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.main.events.model.enums.AdminStateAction;
import ru.practicum.main.location.dto.LocationDto;


import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventAdminRequest {
    @Length(min = 20, max = 2000)
    String annotation;
    Long category;
    @Length(min = 20, max = 7000)
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    LocalDateTime eventDate;
    LocationDto location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    AdminStateAction stateAction;
    @Length(min = 3, max = 120)
    String title;
}