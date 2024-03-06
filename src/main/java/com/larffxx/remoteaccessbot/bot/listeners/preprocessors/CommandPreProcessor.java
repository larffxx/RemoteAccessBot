package com.larffxx.remoteaccessbot.bot.listeners.preprocessors;

import com.larffxx.remoteaccessbot.bot.listeners.commands.Command;
import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CommandPreProcessor {
    private final Collection<Command> commands;

    private Map<String, Command> commandMap = new HashMap<>();

    @PostConstruct
    public void mapCommands() {
        for (Command command : commands) {
            commandMap.put(command.getCommand(), command);
        }
    }

    public Command command(String command) {
        return commandMap.get(command);
    }
    public Command command(CallbackReceiver callbackReceiver){return commandMap.get(callbackReceiver.getData());}
}
