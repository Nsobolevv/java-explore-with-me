package ru.practicum.server.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.exceptions.ValidationRequestException;
import ru.practicum.server.mappers.EndpointHitMapper;
import ru.practicum.server.mappers.ViewStatsMapper;
import ru.practicum.server.repositories.StatServerRepository;
import ru.practicum.server.services.StatService;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class StatServiceImpl implements StatService {
    private final StatServerRepository statServerRepository;
    private final EndpointHitMapper endpointHitMapper;
    private final ViewStatsMapper viewStatsMapper;

    @Override
    public void saveHit(EndpointHitDto endpointHitDto) {
        log.debug("Save hit by app: " + endpointHitDto.getApp());
        statServerRepository.save(endpointHitMapper.toEntity(endpointHitDto));
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.debug("Received stats.");
        if (start.isAfter(end)) {
            throw new ValidationRequestException("Date start must be before date end");
        }
        if (uris == null) {
            return unique ? viewStatsMapper.toEntityList(statServerRepository.findAllUniqueIp(start, end))
                    : viewStatsMapper.toEntityList(statServerRepository.findAllNotUniqueIp(start, end));
        }
        return unique ? viewStatsMapper.toEntityList(statServerRepository.findUniqueIpByUris(start, end, uris))
                : viewStatsMapper.toEntityList(statServerRepository.findNotUniqueIpByUris(start, end, uris));

    }
}
