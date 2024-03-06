package com.larffxx.remoteaccessbot.bot.listeners.keyboards;

import com.larffxx.remoteaccessbot.bot.listeners.buttons.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

@Component
@AllArgsConstructor
public class KeyboardSetup {
    private final DataButton dataButton;
    private final RemoteAccessButton remoteAccessButton;
    private final OpenYoutubeButton openYoutubeButton;
    private final OffPCButton offPCButton;
    private final ScreenshotButton screenshotButton;
    private final MenuButton menuButton;
    private final LastMessageButton lastMessageButton;
    private KeyboardsListener keyboardsListener;

    public void setKeyboards() {
        keyboardsListener.setMenu(InlineKeyboardMarkup.builder().keyboardRow(List.of(dataButton.getDataButton(), remoteAccessButton.getRemoteAccess())).build());
        keyboardsListener.setRemoteAccess(InlineKeyboardMarkup.builder().keyboardRow(List.of(openYoutubeButton.getYoutube(),
                offPCButton.getOff(), screenshotButton.getTakeScreenshot(), menuButton.getMenuButton())).build());
        keyboardsListener.setDataKeyboard(InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(lastMessageButton.getDataFromDB(), menuButton.getMenuButton())).build());
    }
}
