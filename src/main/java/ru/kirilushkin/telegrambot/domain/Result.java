package ru.kirilushkin.telegrambot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private List<Update> result = new ArrayList<>();

    private boolean ok;
}
