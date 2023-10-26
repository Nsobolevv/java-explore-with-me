package ru.practicum.main.compilations.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCompilationDto {
    List<Long> events;
    Boolean pinned;
    @NotBlank
    @Length(max = 50)
    String title;
}