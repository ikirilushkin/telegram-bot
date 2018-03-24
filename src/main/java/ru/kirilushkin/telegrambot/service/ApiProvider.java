package ru.kirilushkin.telegrambot.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kirilushkin.telegrambot.enumeration.ApiMethod;

import java.util.Map;

@Service
public class ApiProvider {

    private static final String API_ENDPOINT = "https://api.telegram.org/bot";

    private String token;


    public String getUpdates(int offset) {
        return new RestTemplate().getForEntity(url(ApiMethod.GET_UPDATES) + "?offset={offset}", String.class, Map.of("offset", offset)).getBody();
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
