package ru.practicum.main.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.requests.model.ParticipationRequest;
import ru.practicum.main.requests.model.enums.RequestStatus;


import java.util.List;

public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {
    List<ParticipationRequest> findAllByRequesterId(long userId);

    List<ParticipationRequest> findAllByEventId(long eventId);

    Integer countByEventIdAndStatus(long eventId, RequestStatus status);

    List<ParticipationRequest> findAllByEventIdAndRequesterId(long eventId, long userId);
}