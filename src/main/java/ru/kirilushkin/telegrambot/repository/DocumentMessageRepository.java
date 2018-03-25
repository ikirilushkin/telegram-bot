package ru.kirilushkin.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirilushkin.telegrambot.enity.DocumentMessage;

public interface DocumentMessageRepository extends JpaRepository<DocumentMessage, Integer>{
}
