package com.larffxx.remoteaccessbot.bot.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


@Component
public class KeyboardsListener {
    @Autowired
    CommandListener commandListener;
    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;
    private final InlineKeyboardButton youtube = InlineKeyboardButton.builder()
            .text("Youtube").callbackData("youtube")
            .build();
    private final InlineKeyboardButton Off = InlineKeyboardButton.builder()
            .text("off").callbackData("off")
            .build();



    public void setKeyboardM1(InlineKeyboardMarkup keyboardM1) {
        this.keyboardM1 = keyboardM1;
    }

    public InlineKeyboardMarkup getKeyboardM1() {
        return keyboardM1;
    }

    public InlineKeyboardButton getYoutube() {
        return youtube;
    }

    public InlineKeyboardButton getOff() {
        return Off;
    }

    public InlineKeyboardMarkup getKeyboardM2() {
        return keyboardM2;
    }

    public void setKeyboardM2(InlineKeyboardMarkup keyboardM2) {
        this.keyboardM2 = keyboardM2;
    }

}
