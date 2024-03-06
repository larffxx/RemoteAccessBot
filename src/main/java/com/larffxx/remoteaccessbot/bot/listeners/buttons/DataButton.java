package com.larffxx.remoteaccessbot.bot.listeners.buttons;

import com.larffxx.remoteaccessbot.bot.listeners.commands.SendMenu;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@AllArgsConstructor
public class DataButton implements Button <SendMessage> {
    private final SendMenu sendMenu;
    private final InlineKeyboardButton dataButton = InlineKeyboardButton.builder()
            .text("Data").callbackData("data")
            .build();

    public InlineKeyboardButton getDataButton() {
        return dataButton;
    }

    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(dataButton.getCallbackData())){
            return sendMenu.execute(updateReceiver,"Data");
        }else return null;
    }
    @Override
    public String getButton() {
        return dataButton.getCallbackData();
    }
}
