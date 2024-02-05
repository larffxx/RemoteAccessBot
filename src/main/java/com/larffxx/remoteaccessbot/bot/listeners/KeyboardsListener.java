package com.larffxx.remoteaccessbot.bot.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


@Component
public class KeyboardsListener {
    @Autowired
    private CommandListener commandListener;

    private InlineKeyboardMarkup menuKeyboard;
    private InlineKeyboardMarkup remoteAccessKeyboard;
    private InlineKeyboardMarkup dataKeyboard;
    private final InlineKeyboardButton youtube = InlineKeyboardButton.builder()
            .text("Open Youtube").callbackData("youtube")
            .build();
    private final InlineKeyboardButton off = InlineKeyboardButton.builder()
            .text("Turn off pc").callbackData("off")
            .build();
    private final InlineKeyboardButton remoteAccess = InlineKeyboardButton.builder()
            .text("Remote Access").callbackData("remoteAccess")
            .build();

    private final InlineKeyboardButton takeScreenshot = InlineKeyboardButton.builder()
            .text("Take screenshot").callbackData("screenshot")
            .build();

    private final InlineKeyboardButton dataButton = InlineKeyboardButton.builder()
            .text("Data").callbackData("data")
            .build();
    private final InlineKeyboardButton dataFromDB = InlineKeyboardButton.builder()
            .text("Data from DB").callbackData("dataFromDB")
            .build();

    public void setMenu(InlineKeyboardMarkup keyboardM1) {
        this.menuKeyboard = keyboardM1;
    }

    public InlineKeyboardMarkup getMenuKeyboard() {
        return menuKeyboard;
    }

    public InlineKeyboardButton getYoutube() {
        return youtube;
    }

    public InlineKeyboardButton getOff() {
        return off;
    }

    public InlineKeyboardMarkup getRemoteAccessKeyboard() {
        return remoteAccessKeyboard;
    }

    public void setRemoteAccess(InlineKeyboardMarkup keyboardM2) {
        this.remoteAccessKeyboard = keyboardM2;
    }

    public InlineKeyboardButton getRemoteAccess() {
        return remoteAccess;
    }

    public InlineKeyboardButton getTakeScreenshot() {
        return takeScreenshot;
    }

    public InlineKeyboardButton getDataButton() {
        return dataButton;
    }

    public InlineKeyboardMarkup getDataKeyboard() {
        return dataKeyboard;
    }

    public void setDataKeyboard(InlineKeyboardMarkup dataKeyboard) {
        this.dataKeyboard = dataKeyboard;
    }

    public InlineKeyboardButton getDataFromDB() {
        return dataFromDB;
    }
}