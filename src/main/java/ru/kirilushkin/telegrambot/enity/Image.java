package ru.kirilushkin.telegrambot.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_size")
    private int fileSize;

    @Column(nullable = true)
    private int height;

    @Column(nullable = true)
    private int weight;


}
