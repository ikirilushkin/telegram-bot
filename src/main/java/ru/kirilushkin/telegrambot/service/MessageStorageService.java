package ru.kirilushkin.telegrambot.service;

import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.DTO.Result;
import ru.kirilushkin.telegrambot.enity.Message;
import ru.kirilushkin.telegrambot.enity.User;

import java.util.Optional;

@Service
public class MessageStorageService {

    private final UserService userService;

    private final MessageService messageService;

    public MessageStorageService(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    public Optional<Integer> store(Result result) {
        Optional<Integer> lastMessageId = Optional.empty();
        for (User user : result.getUsers()) {
            userService.addUser(user);
        }
        for (Message message : result.getMessages()) {
            messageService.save(message);
            lastMessageId = Optional.of(message.getId());
        }
        return lastMessageId;
    }
}
