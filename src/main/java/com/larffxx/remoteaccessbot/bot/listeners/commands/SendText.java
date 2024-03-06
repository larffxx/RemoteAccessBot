package com.larffxx.remoteaccessbot.bot.listeners.commands;

import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Getter
public class SendText implements Command<SendMessage> {
    @Override
    public SendMessage execute(UpdateReceiver update) {
        SendMessage sm = SendMessage.builder()
                .chatId(String.valueOf(update.getPcId()))
                .text("Text").build();
        return sm;
    }
    public SendMessage execute(UpdateReceiver update, String text){
        SendMessage sm = SendMessage.builder()
                .chatId(String.valueOf(update.getPcId()))
                .text(text).build();
        return sm;
    }

    @Override
    public String getCommand() {
        return "/sendText";
    }
}
