package ru.kirilushkin.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirilushkin.telegrambot.enity.PhotoMessage;

public interface PhotoMessageRepository extends JpaRepository<PhotoMessage, Integer> {
}
