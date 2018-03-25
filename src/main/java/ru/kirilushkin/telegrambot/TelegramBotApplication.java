package ru.kirilushkin.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.kirilushkin.telegrambot.enity.Message;
import ru.kirilushkin.telegrambot.service.MessageService;
import ru.kirilushkin.telegrambot.service.PollingService;

import java.util.List;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(TelegramBotApplication.class, args);
		PollingService pollingService = context.getBean(PollingService.class);
		pollingService.run();

		MessageService messageService = context.getBean(MessageService.class);
		List<Message> all = messageService.findAll();
		System.out.println(all);
	}
}
