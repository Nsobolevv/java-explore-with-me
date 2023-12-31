package ru.practicum.main.users.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main.users.model.User;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id in ?1")
    List<User> findAllByIds(List<Long> ids, Pageable pageable);
}