package com.larffxx.remoteaccessbot.bot.listeners.commands;

import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class SendRemoteAccess implements Command<SendMessage> {
    private KeyboardsListener keyboardsListener;

    public SendRemoteAccess(KeyboardsListener keyboardsListener) {
        this.keyboardsListener = keyboardsListener;
    }

    public SendMessage execute(UpdateReceiver update) {
        SendMessage sm = SendMessage.builder().chatId(update.getUpdate().getMessage().getChatId().toString())
                .parseMode("HTML").text("Menu")
                .replyMarkup(keyboardsListener.getRemoteAccessKeyboard()).build();
        return sm;
    }

    @Override
    public String getCommand() {
        return "/remoteAccess";
    }
}
