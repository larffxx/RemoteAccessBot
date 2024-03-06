package com.larffxx.remoteaccessbot.bot.listeners.buttons;

import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.listeners.commands.SendText;
import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@Getter
@AllArgsConstructor
public class OpenYoutubeButton implements Button <SendMessage>{
    CommandListener commandListener;
    SendText sendText;
    private final InlineKeyboardButton youtube = InlineKeyboardButton.builder()
            .text("Open Youtube").callbackData("youtube")
            .build();

    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(youtube.getCallbackData())){
            commandListener.openYoutube();
            return sendText.execute(updateReceiver, "Youtube was opened");
        }
        return null;
    }
    @Override
    public String getButton() {
        return youtube.getCallbackData();
    }
}
