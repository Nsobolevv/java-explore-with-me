package ru.practicum.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewStatsDto {
    private String app;
    private String uri;
    private int hits;
}