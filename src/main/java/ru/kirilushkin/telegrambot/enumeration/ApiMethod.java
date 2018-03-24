package ru.kirilushkin.telegrambot.enumeration;

public enum ApiMethod {
    DELETE_WEBHOOK("deleteWebhook"),
    GET_UPDATES("getUpdates"),
    SEND_MESSAGE("sendMessage");

    private final String method;

    ApiMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return method;
    }
}
