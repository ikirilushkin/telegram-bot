package ru.kirilushkin.telegrambot.service;

import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.enity.User;
import ru.kirilushkin.telegrambot.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void addUser(User user) {
        Optional<User> userById = userRepository.findById(user.getId());
        if (!userById.isPresent()) {
            userRepository.save(user);
        }

    }
}
