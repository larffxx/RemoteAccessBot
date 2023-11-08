package com.larffxx.remoteaccessbot;

import com.larffxx.remoteaccessbot.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class RemoteAccessBotApplication {
	public static void main(String[] args) throws TelegramApiException {
		ConfigurableApplicationContext context = SpringApplication.run(RemoteAccessBotApplication.class, args);
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		Bot bot = context.getBean("bot", Bot.class);
		botsApi.registerBot(bot);
	}

}
