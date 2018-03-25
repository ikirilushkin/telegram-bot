package ru.kirilushkin.telegrambot.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kirilushkin.telegrambot.deserializer.ResultDeserializer;
import ru.kirilushkin.telegrambot.enity.Message;
import ru.kirilushkin.telegrambot.enity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ResultDeserializer.class)
public class Result {

    private List<Message> messages = new ArrayList<>();

    private Set<User> users = new HashSet<>();

    private int lastUpdatedId;

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addUser(User user) {
        users.add(user);
    }
}
