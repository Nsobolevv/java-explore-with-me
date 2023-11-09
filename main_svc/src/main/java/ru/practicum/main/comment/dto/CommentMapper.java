package ru.practicum.main.comment.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.main.comment.model.Comment;
import ru.practicum.main.constants.Pattern;
import ru.practicum.main.events.dto.mapper.EventMapper;
import ru.practicum.main.users.dto.mapper.UserMapper;


import java.time.format.DateTimeFormatter;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Pattern.DATE);

    public static Comment toComment(NewCommentDto newCommentDto) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .build();
    }

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .event(EventMapper.toShortDto(comment.getEvent()))
                .author(UserMapper.toShortDto(comment.getAuthor()))
                .text(comment.getText())
                .state(comment.getState().toString())
                .createdOn(comment.getCreatedOn().format(formatter))
                .updatedOn(comment.getUpdatedOn() != null ? comment.getUpdatedOn().format(formatter) : null)
                .publishedOn(comment.getPublishedOn() != null ? comment.getPublishedOn().format(formatter) : null)
                .build();
    }
}