package ru.kirilushkin.telegrambot.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentMessage extends Message {

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "document_name")
    private String fileName;

    @Column(name = "document_id")
    private String fileId;

    @Column(name = "document_size")
    private int fileSize;

    @Embedded
    private Image thumb;
}
