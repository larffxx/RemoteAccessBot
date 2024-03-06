package com.larffxx.remoteaccessbot.bot.listeners.buttons;

import com.larffxx.remoteaccessbot.bot.listeners.commands.SendMenu;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@AllArgsConstructor
@Getter
public class MenuButton implements Button <SendMessage>{
    SendMenu sendMenu;
    private final InlineKeyboardButton menuButton = InlineKeyboardButton.builder()
            .text("Menu").callbackData("menu")
            .build();


    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(menuButton.getCallbackData())){
            return sendMenu.execute(updateReceiver,"Menu");
        }else return null;
    }

    @Override
    public String getButton() {
        return menuButton.getCallbackData();
    }
}
