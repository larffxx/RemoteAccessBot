package com.larffxx.remoteaccessbot.bot.listeners.commands;

import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface Command <T> {
    T execute (UpdateReceiver update);
    String getCommand();

}
