package ru.kirilushkin.telegrambot.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoMessage extends Message {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "photo_images")
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
    }
}
