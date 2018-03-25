package ru.kirilushkin.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirilushkin.telegrambot.enity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
