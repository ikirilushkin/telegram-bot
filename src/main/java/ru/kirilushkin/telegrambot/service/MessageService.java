package ru.kirilushkin.telegrambot.service;

import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.enity.Message;
import ru.kirilushkin.telegrambot.repository.DocumentMessageRepository;
import ru.kirilushkin.telegrambot.repository.MessageRepository;
import ru.kirilushkin.telegrambot.repository.PhotoMessageRepository;
import ru.kirilushkin.telegrambot.repository.TextMessageRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {

    private final DocumentMessageRepository documentMessageRepository;

    private final PhotoMessageRepository photoMessageRepository;

    private final TextMessageRepository textMessageRepository;

    private final MessageRepository messageRepository;


    public MessageService(DocumentMessageRepository documentMessageRepository, PhotoMessageRepository photoMessageRepository, TextMessageRepository textMessageRepository, MessageRepository messageRepository) {
        this.documentMessageRepository = documentMessageRepository;
        this.photoMessageRepository = photoMessageRepository;
        this.textMessageRepository = textMessageRepository;
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        messages.addAll(documentMessageRepository.findAll());
        messages.addAll(photoMessageRepository.findAll());
        messages.addAll(textMessageRepository.findAll());
        Collections.sort(messages, Comparator.comparingInt(Message::getId));
        return messages;
    }

    public void save(Message message) {
        messageRepository.save(message);
    }
}
