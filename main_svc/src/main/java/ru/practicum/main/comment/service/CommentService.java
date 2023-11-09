package ru.practicum.main.comment.service;


import ru.practicum.main.comment.dto.CommentResponseDto;
import ru.practicum.main.comment.dto.NewCommentDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(Long userId, Long eventId, NewCommentDto newCommentDto);

    List<CommentResponseDto> getEventComments(Long eventId, int from, int size);

    CommentResponseDto getCommentById(Long commentId);

    CommentResponseDto updateComment(Long userId, Long commentId, NewCommentDto newCommentDto);

    void deleteComment(Long userId, Long commentId);

    CommentResponseDto updateCommentStatusByAdmin(Long commentId, boolean isConfirm);
}