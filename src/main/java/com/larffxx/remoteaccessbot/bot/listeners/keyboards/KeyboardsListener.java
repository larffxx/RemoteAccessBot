package com.larffxx.remoteaccessbot.bot.listeners.keyboards;

import com.larffxx.remoteaccessbot.bot.listeners.buttons.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;


@Component
@RequiredArgsConstructor
public class KeyboardsListener {
    private InlineKeyboardMarkup menuKeyboard;
    private InlineKeyboardMarkup remoteAccessKeyboard;
    private InlineKeyboardMarkup dataKeyboard;

    public void setMenu(InlineKeyboardMarkup keyboardM1) {
        this.menuKeyboard = keyboardM1;
    }

    public InlineKeyboardMarkup getMenuKeyboard() {
        return menuKeyboard;
    }

    public InlineKeyboardMarkup getRemoteAccessKeyboard() {
        return remoteAccessKeyboard;
    }

    public void setRemoteAccess(InlineKeyboardMarkup keyboardM2) {
        this.remoteAccessKeyboard = keyboardM2;
    }

    public InlineKeyboardMarkup getDataKeyboard() {
        return dataKeyboard;
    }

    public void setDataKeyboard(InlineKeyboardMarkup dataKeyboard) {
        this.dataKeyboard = dataKeyboard;
    }

}