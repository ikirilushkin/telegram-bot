package ru.kirilushkin.telegrambot.enumeration;

public enum MessageType {
    Text("text"),
    Photo("photo"),
    Document("document");

    private String text;

    MessageType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
