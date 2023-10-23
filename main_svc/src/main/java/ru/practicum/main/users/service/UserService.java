package ru.practicum.main.users.service;



import ru.practicum.main.users.dto.NewUserRequestDto;
import ru.practicum.main.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> get(List<Long> ids, int from, int size);

    UserDto add(NewUserRequestDto newUser);

    void delete(long userId);
}