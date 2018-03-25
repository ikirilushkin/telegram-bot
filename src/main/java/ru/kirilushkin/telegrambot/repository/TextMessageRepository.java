package ru.kirilushkin.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirilushkin.telegrambot.enity.TextMessage;

public interface TextMessageRepository extends JpaRepository<TextMessage, Integer> {
}
