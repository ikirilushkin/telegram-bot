package ru.kirilushkin.telegrambot.exception;

public class UnsupportedMessageType extends RuntimeException {

    public UnsupportedMessageType() {
        super();
    }

    public UnsupportedMessageType(String message) {
        super(message);
    }

    public UnsupportedMessageType(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedMessageType(Throwable cause) {
        super(cause);
    }

    protected UnsupportedMessageType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
