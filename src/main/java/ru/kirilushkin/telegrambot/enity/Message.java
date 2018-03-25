package ru.kirilushkin.telegrambot.enity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
