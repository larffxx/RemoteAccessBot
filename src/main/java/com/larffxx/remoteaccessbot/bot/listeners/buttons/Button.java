package com.larffxx.remoteaccessbot.bot.listeners.buttons;

import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import org.springframework.stereotype.Component;

@Component
public interface Button <T> {
    T clicked(UpdateReceiver updateReceiver);
    String getButton();
}
