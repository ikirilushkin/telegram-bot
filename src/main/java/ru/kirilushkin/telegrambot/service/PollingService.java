package ru.kirilushkin.telegrambot.service;

import org.springframework.stereotype.Service;
import ru.kirilushkin.telegrambot.DTO.Result;

import java.util.Optional;

@Service
public class PollingService {

    private final ApiProvider apiProvider;

    private final MessageStorageService storageService;


    public PollingService(ApiProvider apiProvider, MessageStorageService storageService) {
        this.apiProvider = apiProvider;
        this.storageService = storageService;
    }

    public void run(){
        apiProvider.deleteWebHook();
        int lastUpdateId = 0;

        Result result = apiProvider.getUpdates(lastUpdateId);
        Optional<Integer> storedMessageId = storageService.store(result);
        if (storedMessageId.isPresent()) {
            //lastUpdateId = storedMessageId.get();
            System.out.println(storedMessageId.get());
        }
    }
}
