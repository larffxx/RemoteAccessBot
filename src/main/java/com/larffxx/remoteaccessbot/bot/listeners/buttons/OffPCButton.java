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
@AllArgsConstructor
@Getter
public class OffPCButton implements Button<SendMessage> {
    private CommandListener commandListener;
    private SendText sendText;
    private final InlineKeyboardButton off = InlineKeyboardButton.builder()
            .text("Turn off pc").callbackData("off")
            .build();

    @Override
    public SendMessage clicked(UpdateReceiver updateReceiver) {
        if (updateReceiver.getUpdate().getCallbackQuery().getData().equals(off.getCallbackData())) {
            sendText.execute(updateReceiver, "Turning off pc");
            commandListener.offPc();
            return new SendMessage();
        }return null;
    }

    @Override
    public String getButton() {
        return off.getCallbackData();
    }
}
