package ru.practicum.main.users.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main.users.dto.NewUserRequestDto;
import ru.practicum.main.users.dto.UserDto;
import ru.practicum.main.users.dto.UserShortDto;
import ru.practicum.main.users.model.User;


@UtilityClass
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static User toUser(NewUserRequestDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public static UserShortDto toShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}