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
public class RemoteAccessButton implements Button<SendMessage> {
    private SendMenu sendMenu;
    private final InlineKeyboardButton remoteAccess = InlineKeyboardButton.builder()
            .text("Remote Access").callbackData("remoteAccess")
            .build();
    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(remoteAccess.getCallbackData())){
            return sendMenu.execute(updateReceiver,"RemoteAccess");
        }return null;
    }
    @Override
    public String getButton() {
        return remoteAccess.getCallbackData();
    }
}
