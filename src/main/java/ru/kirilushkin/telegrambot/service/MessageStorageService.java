package ru.kirilushkin.telegrambot.service;

import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.domain.Message;
import ru.kirilushkin.telegrambot.domain.Update;
import ru.kirilushkin.telegrambot.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class MessageStorageService {

    private final UserService userService;

    public MessageStorageService(UserService userService) {
        this.userService = userService;
    }

    public Optional<Integer> store(List<Update> updates) {
        Optional<Integer> lastMessageId = Optional.empty();
        for (Update update : updates) {
            System.out.println(update.getId());
            Message message = update.getMessage();
            User user = message.getUser();
            System.out.println(user.getId());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getUsername());
            lastMessageId = Optional.of(message.getId());
        }
        return lastMessageId;
    }
}
