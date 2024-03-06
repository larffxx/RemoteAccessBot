package com.larffxx.remoteaccessbot.bot.listeners.buttons;

import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.listeners.commands.SendPhotoMessage;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@Getter
@AllArgsConstructor
public class ScreenshotButton implements Button <SendPhoto> {
    SendPhotoMessage sendPhotoMessage;
    private final InlineKeyboardButton takeScreenshot = InlineKeyboardButton.builder()
            .text("Take screenshot").callbackData("screenshot")
            .build();

    @Override
    public SendPhoto clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(takeScreenshot.getCallbackData())){
            CommandListener.takeScreenshot();
            return sendPhotoMessage.execute(updateReceiver);
        }
        return null;
    }
    @Override
    public String getButton() {
        return takeScreenshot.getCallbackData();
    }
}
