package ru.practicum.main.requests.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.main.requests.model.enums.RequestStatus;


import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationRequestDto {
    LocalDateTime created;
    long event;
    long id;
    long requester;
    RequestStatus status;
}