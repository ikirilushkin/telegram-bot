package ru.kirilushkin.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirilushkin.telegrambot.enity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
}
