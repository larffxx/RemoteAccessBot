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
public class LastMessageButton implements Button <SendMessage>{
    private SendText sendText;
    private CommandListener commandListener;

    private final InlineKeyboardButton dataFromDB = InlineKeyboardButton.builder()
            .text("Last Message").callbackData("dataFromDB")
            .build();

    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if(updateReceiver.getUpdate().getCallbackQuery().getData().equals(dataFromDB.getCallbackData())){
            return sendText.execute(updateReceiver, "Last message from you" + "\n" +commandListener.getDataFromDB());
        }
        return null;
    }
    @Override
    public String getButton() {
        return dataFromDB.getCallbackData();
    }
}
