package ru.practicum.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.exceptions.StatsException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatClient {
    private final String serverUrl;
    private final RestTemplate restTemplate;

    public StatClient(@Value("${app.statsUri}") String serverUrl, RestTemplate restTemplate) {
        this.serverUrl = serverUrl;
        this.restTemplate = restTemplate;
    }

    public void addStats(EndpointHitDto endpointHitDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EndpointHitDto> requestEntity = new HttpEntity<>(endpointHitDto, headers);
        restTemplate.exchange(serverUrl + "/hit", HttpMethod.POST, requestEntity, EndpointHitDto.class);
    }

    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, Boolean unique) {

        Map<String, Object> parameters = new HashMap<>();
        String path;
        if (uris == null) {
            parameters.put("start", start);
            parameters.put("end", end);
            parameters.put("unique", unique);
            path = serverUrl + "/stats?start={start}&end={end}&unique={unique}";
        } else {
            parameters.put("start", start);
            parameters.put("end", end);
            parameters.put("uris", uris);
            parameters.put("unique", unique);
            path = serverUrl + "/stats?start={start}&end={end}&uris={uris}&unique={unique}";
        }

        ResponseEntity<String> response = restTemplate.getForEntity(path, String.class, parameters);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return Arrays.asList(objectMapper.readValue(response.getBody(), ViewStatsDto[].class));
        } catch (JsonProcessingException exception) {
            throw new StatsException(String.format("Json processing error: %s", exception.getMessage()));
        }
    }
}

