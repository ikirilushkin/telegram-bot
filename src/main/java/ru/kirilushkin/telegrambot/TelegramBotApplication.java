package ru.kirilushkin.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.kirilushkin.telegrambot.service.PollingService;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(TelegramBotApplication.class, args);
		PollingService pollingService = context.getBean(PollingService.class);
		pollingService.run();
	}
}
