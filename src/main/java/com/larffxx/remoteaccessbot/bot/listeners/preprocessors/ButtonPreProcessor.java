package com.larffxx.remoteaccessbot.bot.listeners.preprocessors;

import com.larffxx.remoteaccessbot.bot.listeners.buttons.Button;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@AllArgsConstructor
public class ButtonPreProcessor {
    private final Collection<Button> buttons;

    private Map<String, Button> buttonMap;

    @PostConstruct
    public void mapButtons() {
        for (Button button : buttons) {
            buttonMap.put(button.getButton(), button);
        }
    }

    public Button button(String command) {
        return buttonMap.get(command);
    }
    public Button button(UpdateReceiver updateReceiver){return buttonMap.get(updateReceiver.getUpdate().getCallbackQuery().getData());}
}
