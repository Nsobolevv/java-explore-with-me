package ru.practicum.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatClient {
    private final String serverUrl = "http://stats-server:9090";
    private final RestTemplate restTemplate;

    public StatClient() {
        this.restTemplate = new RestTemplate();
    }

    public void addStats(EndpointHitDto endpointHitDto) {
        HttpEntity<EndpointHitDto> requestEntity = new HttpEntity<>(endpointHitDto);
        restTemplate.exchange(serverUrl + "/hit", HttpMethod.POST, requestEntity, Object.class);
    }

    public ResponseEntity<ViewStatsDto[]> getStats(String start, String end, String[] uris, Boolean unique) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start", start);
        parameters.put("end", end);
        parameters.put("unique", unique);
        String path = serverUrl + "/stats?start={start}&end={end}&unique={unique}";
        if (uris != null) {
            parameters.put("uris", uris);
            path += "&uris={uris}";
        }

        ResponseEntity<ViewStatsDto[]> serverResponse = restTemplate.getForEntity(path, ViewStatsDto[].class, parameters);
        if (serverResponse.getStatusCode().is2xxSuccessful()) {
            return serverResponse;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(serverResponse.getStatusCode());
        if (serverResponse.hasBody()) {
            return responseBuilder.body(serverResponse.getBody());
        }
        return responseBuilder.build();
    }
}

