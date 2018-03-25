package ru.kirilushkin.telegrambot.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "bot")
    private boolean bot;

//    @OneToMany(mappedBy = "user")
//    private List<Message> messages = new ArrayList<>();

    public User(int id, String firstName, boolean bot) {
        this.id = id;
        this.firstName = firstName;
        this.bot = bot;
    }
}
