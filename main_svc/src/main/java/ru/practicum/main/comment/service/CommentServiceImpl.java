package ru.practicum.main.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.main.comment.dto.CommentMapper;
import ru.practicum.main.comment.dto.CommentResponseDto;
import ru.practicum.main.comment.dto.NewCommentDto;
import ru.practicum.main.comment.model.Comment;
import ru.practicum.main.comment.model.enums.CommentState;
import ru.practicum.main.comment.repository.CommentRepository;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.events.model.enums.State;
import ru.practicum.main.events.repository.EventRepository;
import ru.practicum.main.exceptions.CommentNotFoundException;
import ru.practicum.main.exceptions.EventNotFoundException;
import ru.practicum.main.exceptions.ForbiddenArgumentException;
import ru.practicum.main.exceptions.UserNotFoundException;
import ru.practicum.main.users.model.User;
import ru.practicum.main.users.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.main.comment.dto.CommentMapper.toComment;
import static ru.practicum.main.comment.dto.CommentMapper.toCommentResponseDto;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public CommentResponseDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto) {
        log.info("Create comment from user with ID = " + userId + ", for event with ID = " + eventId + ": " + newCommentDto);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id=%d was not found", userId)));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(String.format("Event with id=%d was not found", eventId)));
        if (event.getState() != State.PUBLISHED) {
            throw new EventNotFoundException(String.format("Event with id=%d was not found", eventId));
        }
        Comment comment = toComment(newCommentDto);
        comment.setEvent(event);
        comment.setAuthor(user);
        comment.setState(CommentState.PENDING);
        comment.setCreatedOn(LocalDateTime.now());
        return toCommentResponseDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponseDto> getEventComments(Long eventId, int from, int size) {
        log.info("Get comments for event with ID = " + eventId);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(String.format("Event with id=%d was not found", eventId)));
        if (event.getState() != State.PUBLISHED) {
            throw new EventNotFoundException(String.format("Event with id=%d was not found", eventId));
        }
        List<Comment> comments = commentRepository.findByEvent(event, PageRequest.of(from / size, size));
        return comments.stream().map(CommentMapper::toCommentResponseDto).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        log.info("Get comment by ID = " + commentId);
        return toCommentResponseDto(commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId))));
    }

    @Override
    public CommentResponseDto updateComment(Long userId, Long commentId, NewCommentDto newCommentDto) {
        log.info("Update comment with ID = " + commentId + " from user with ID = " + userId + ": " + newCommentDto);
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id=%d was not found", userId)));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId)));
        if (comment.getAuthor().getId() != userId) {
            throw new ForbiddenArgumentException("Can not update comment from other user.");
        }
        if (comment.getState() == CommentState.CONFIRMED) {
            throw new ForbiddenArgumentException("Can not update confirmed comment.");
        }
        comment.setText(newCommentDto.getText());
        comment.setUpdatedOn(LocalDateTime.now());
        comment.setState(CommentState.PENDING);
        return toCommentResponseDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        log.info("Delete comment with ID = " + commentId + " from user with ID = " + userId);
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id=%d was not found", userId)));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId)));
        if (!(comment.getAuthor().getId() == userId)) {
            throw new ForbiddenArgumentException("Can not delete comment from other user.");
        }
        if (comment.getState() == CommentState.CONFIRMED) {
            throw new ForbiddenArgumentException("Can not delete confirmed comment.");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponseDto updateCommentStatusByAdmin(Long commentId, boolean isConfirm) {
        log.info("Confirm/reject comment with ID = " + commentId + ". New state: " + isConfirm);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Comment with id=%d was not found", commentId)));
        comment.setState(isConfirm ? CommentState.CONFIRMED : CommentState.REJECTED);
        comment.setPublishedOn(LocalDateTime.now());
        return toCommentResponseDto(commentRepository.save(comment));
    }
}