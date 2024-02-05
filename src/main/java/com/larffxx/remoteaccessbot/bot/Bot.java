package com.larffxx.remoteaccessbot.bot;

import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.listeners.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import com.larffxx.remoteaccessbot.models.TextData;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    private final SendMessage MESSAGE = new SendMessage();

    @Autowired
    CallbackReceiver callbackReceiver;

    @Autowired
    CommandListener commandListener;
    @Autowired
    KeyboardsListener keyboardsListener;
    @Autowired
    private TextDataRepository textDataRepository;
    @Value("${name}")
    private String botUsername;
    @Value("${token}")
    private String botToken;
    @Autowired
    private UpdateReceiver updateReceiver;
    @Autowired
    public Bot() {
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    //Main method to work with Telegram API
    @Override
    public void onUpdateReceived(Update update) {
        setKeyboards();
        if (update.hasCallbackQuery()) {
            callbackReceiver.getCallBackQueryData(update);
            switch (callbackReceiver.getData()) {
                case "remoteAccess" ->
                        sendMenu(updateReceiver.getUpdate().getMessage().getFrom().getId(), "All remote access futures", keyboardsListener.getRemoteAccessKeyboard());
                case "off" -> {
                    MESSAGE.setText("Turning off pc");
                    try {
                        execute(MESSAGE);
                        commandListener.offPc();
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "youtube" -> {
                    MESSAGE.setText("Open Youtube");
                    try {
                        execute(MESSAGE);
                        commandListener.openYoutube();
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "screenshot" -> {
                    MESSAGE.setText("Taking screenshot");
                    CommandListener.takeScreenshot();
                    sendPhotoMessage(callbackReceiver.getMessage().getChatId(), "Here is your screen");
                }
                case "data" ->
                        sendMenu(updateReceiver.getUpdate().getMessage().getFrom().getId(), "All about Data", keyboardsListener.getDataKeyboard());
                case "dataFromDB" -> {
                    MESSAGE.setText("Last message from any user: " + "\n" + commandListener.getDataFromDB());
                    try {
                        execute(MESSAGE);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        saveToUpdateReceiver(update);
        MESSAGE.setChatId(String.valueOf(updateReceiver.getUpdate().getMessage().getChatId()));
        if (updateReceiver.getUpdate().getMessage().getText().equals("/menu")) {
            sendMenu(updateReceiver.getUpdate().getMessage().getFrom().getId(), "Menu", keyboardsListener.getMenuKeyboard());
        } else if (!updateReceiver.getUpdate().getMessage().getText().startsWith("/")) {
            sendText(updateReceiver.getUpdate().getMessage().getFrom().getId(), "Use /menu");
        }

        updateReceiver.saveData(updateReceiver.getUpdate().getMessage().getFrom().getFirstName(), update.getMessage().getText());
    }

        public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb) {
            SendMessage sm = SendMessage.builder().chatId(who.toString())
                    .parseMode("HTML").text(txt)
                    .replyMarkup(kb).build();

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendText(Long who, String what) {
            SendMessage sm = SendMessage.builder()
                    .chatId(who.toString())
                    .text(what).build();
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendPhotoMessage(long chatId, String caption) {

            SendPhoto sendPhoto = SendPhoto.builder()
                    .chatId(String.valueOf(chatId))
                    .photo(new InputFile(new File("C:\\Users\\offic\\IdeaProjects\\RemoteAccessBot\\src\\main\\resources\\screenshots\\" + commandListener.getLastNum() + ".jpg")))
                    .caption(caption)
                    .parseMode(ParseMode.HTML)
                    .build();

            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }


        public void saveToUpdateReceiver(Update update) {
            updateReceiver.setUpdate(update);
        }

        public void setKeyboards() {
            keyboardsListener.setMenu(InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardsListener.getDataButton(), keyboardsListener.getRemoteAccess())).build());
            keyboardsListener.setRemoteAccess(InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardsListener.getYoutube(),
                    keyboardsListener.getOff(), keyboardsListener.getTakeScreenshot())).build());
            keyboardsListener.setDataKeyboard(InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardsListener.getDataFromDB())).build());
        }
}

