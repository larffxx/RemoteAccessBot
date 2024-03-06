package com.larffxx.remoteaccessbot.bot.listeners.commands;

import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Setter
@Getter
public class SendMenu implements Command<SendMessage> {
    final
    KeyboardsListener keyboardsListener;

    public SendMenu(KeyboardsListener keyboardsListener) {
        this.keyboardsListener = keyboardsListener;
    }

    @Override
    public SendMessage execute(UpdateReceiver update) {
        SendMessage sm = SendMessage.builder().chatId(update.getUpdate().getMessage().getChatId().toString())
                .parseMode("HTML").text("Menu")
                .replyMarkup(keyboardsListener.getMenuKeyboard()).build();
        return sm;
    }

    public SendMessage execute(UpdateReceiver update, String text) {
        SendMessage sm = null;
        switch (text) {
            case "Data" -> sm = SendMessage.builder().chatId(String.valueOf(update.getPcId()))
                    .parseMode("HTML").text("All about Data")
                    .replyMarkup(keyboardsListener.getDataKeyboard()).build();
            case "Menu" -> sm = SendMessage.builder().chatId(String.valueOf(update.getPcId()))
                    .parseMode("HTML").text("Menu")
                    .replyMarkup(keyboardsListener.getMenuKeyboard()).build();
            case "RemoteAccess" -> sm = SendMessage.builder().chatId(String.valueOf(update.getPcId()))
                    .parseMode("HTML").text("All Remote Access Futures")
                    .replyMarkup(keyboardsListener.getRemoteAccessKeyboard()).build();
        }
        return sm;
    }

    @Override
    public String getCommand() {
        return "/menu";
    }
}
