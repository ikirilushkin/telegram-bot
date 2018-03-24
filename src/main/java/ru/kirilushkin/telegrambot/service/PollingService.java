package ru.kirilushkin.telegrambot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.domain.Message;
import ru.kirilushkin.telegrambot.domain.Result;
import ru.kirilushkin.telegrambot.domain.Update;
import ru.kirilushkin.telegrambot.exception.ApiException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PollingService {

    private final ApiProvider apiProvider;

    private final MessageStorageService storageService;

    private final ObjectMapper objectMapper;

    public PollingService(ApiProvider apiProvider, MessageStorageService storageService, @Qualifier("mapper") ObjectMapper objectMapper) {
        this.apiProvider = apiProvider;
        this.storageService = storageService;
        this.objectMapper = objectMapper;
    }

    public void run() throws InterruptedException {
        apiProvider.deleteWebHook();
        int lastUpdateId = 0;

        while (true) {
            System.out.println(lastUpdateId);
            String response = apiProvider.getUpdates(lastUpdateId);
            try {
                Result result = objectMapper.readValue(response, Result.class);
                if (!result.isOk()) {
                    throw new ApiException("Get Updates return false");
                }
                Optional<Integer> storedMessageId = storageService.store(result.getResult());
                if (storedMessageId.isPresent()) {
                    //lastUpdateId = storedMessageId.get();
                    System.out.println(storedMessageId.get());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }

    }
}
