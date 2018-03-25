package ru.kirilushkin.telegrambot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kirilushkin.telegrambot.DTO.Result;
import ru.kirilushkin.telegrambot.enumeration.ApiMethod;
import ru.kirilushkin.telegrambot.exception.ApiException;

import java.io.IOException;
import java.util.Map;

@Service
public class ApiProvider {

    private final ObjectMapper objectMapper;

    public ApiProvider(@Qualifier("mapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final String API_ENDPOINT = "https://api.telegram.org/bot";

    private String token;


    public Result getUpdates(int offset) {
        String response = new RestTemplate()
                .getForEntity(
                        url(ApiMethod.GET_UPDATES) + "?offset={offset}",
                        String.class,
                        Map.of("offset", offset)
                ).getBody();
        try {
            return objectMapper.readValue(response, Result.class);
        } catch (IOException e) {
            throw new ApiException(""); //todo
        }
    }

    private String url(ApiMethod method) {
        return API_ENDPOINT + token + "/" + method;
    }

    public void deleteWebHook() {
        new RestTemplate().postForEntity(url(ApiMethod.DELETE_WEBHOOK), null, String.class);
    }

    @Value("${telegram.token}")
    public void setToken(String token) {
        this.token = token;
    }
}
