package ru.practicum.dto;

import lombok.*;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class EndpointHitDto {
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}
