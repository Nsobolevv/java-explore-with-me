package ru.practicum.server.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.server.constants.Pattern;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hits", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String app;
    @Column(nullable = false)
    private String uri;
    @Column(nullable = false, length = 40)
    private String ip;
    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "datetime", nullable = false)
    private LocalDateTime timestamp;
}