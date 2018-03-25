package ru.kirilushkin.telegrambot.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.kirilushkin.telegrambot.DTO.Result;
import ru.kirilushkin.telegrambot.enity.*;
import ru.kirilushkin.telegrambot.enumeration.MessageType;
import ru.kirilushkin.telegrambot.exception.UnsupportedMessageType;

import java.io.IOException;

public class ResultDeserializer extends StdDeserializer<Result> {

    protected ResultDeserializer() {
        this(null);
    }

    protected ResultDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Result deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Result result = new Result();
        JsonNode resultNode = p.getCodec().readTree(p);
        JsonNode messageListNode = resultNode.get("result");
        for (JsonNode jsonNode : messageListNode) {
            User user = deserializeUser(jsonNode, result);
            deserializeMessage(jsonNode, result, user);
        }
        return result;
    }

    private User deserializeUser(JsonNode jsonNode, Result result) {
        JsonNode userNode = jsonNode.get("message").get("from");
        User user = new User(
                userNode.get("id").asInt(),
                userNode.get("first_name").asText(),
                userNode.get("is_bot").asBoolean());
        if (userNode.has("last_name")) {
            user.setLastName(userNode.get("last_name").asText());
        }
        if (userNode.has("username")) {
            user.setUsername(userNode.get("username").asText());
        }
        result.addUser(user);
        return user;
    }

    private void deserializeMessage(JsonNode jsonNode, Result result, User user) {
        int updateId = jsonNode.get("update_id").asInt();
        result.setLastUpdatedId(updateId);
        JsonNode messageNode = jsonNode.get("message");
        Message message = deserializeMessageInternal(messageNode);
        message.setId(deserializeMessageId(messageNode));
//        message.setUser(user);
        result.addMessage(message);
    }

    private Message deserializeMessageInternal(JsonNode messageNode) {
        if (messageNode.has(MessageType.Text.getText())) {
            return createTextMessage(messageNode);
        } else if (messageNode.has(MessageType.Document.getText())) {
            return createDocumentMessage(messageNode);
        } else if (messageNode.has(MessageType.Photo.getText())) {
            return createPhotoMessage(messageNode);
        } else {
            throw new UnsupportedMessageType("Unsupported message type: " + messageNode);
        }
    }

    private PhotoMessage createPhotoMessage(JsonNode messageNode) {
        PhotoMessage photoMessage = new PhotoMessage();
        JsonNode photoNode = messageNode.get("photo");
        for (JsonNode imageNode : photoNode) {
            photoMessage.addImage(deserializeImage(imageNode));
        }
        return photoMessage;
    }

    private DocumentMessage createDocumentMessage(JsonNode messageNode) {
        DocumentMessage documentMessage = new DocumentMessage();
        JsonNode documentNode = messageNode.get("document");
        documentMessage.setMimeType(documentNode.get("mime_type").asText());
        documentMessage.setFileId(documentNode.get("file_id").asText());
        documentMessage.setFileName(documentNode.get("file_name").asText());
        documentMessage.setFileSize(documentNode.get("file_size").asInt());
        if (documentNode.has("thumb")) {
            documentMessage.setThumb(deserializeImage(documentNode.get("thumb")));
        }
        return documentMessage;
    }

    private TextMessage createTextMessage(JsonNode messageNode) {
        TextMessage textMessage = new TextMessage();
        textMessage.setText(messageNode.get("text").asText());
        return textMessage;
    }

    private int deserializeMessageId(JsonNode messageNode) {
        return messageNode.get("message_id").asInt();
    }

    private Image deserializeImage(JsonNode imageNode) {
        Image image = Image.builder()
                .fileId(imageNode.get("file_id").asText())
                .fileSize(imageNode.get("file_size").asInt())
                .height(imageNode.get("height").asInt())
                .weight(imageNode.get("width").asInt())
                .build();
        return image;
    }
}
