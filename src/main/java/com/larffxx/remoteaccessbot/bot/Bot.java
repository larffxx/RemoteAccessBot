package com.larffxx.remoteaccessbot.bot;

import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.listeners.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
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
    private boolean enabled;

    @Autowired
    public Bot() {
    }

    public void setEnabled(boolean b) {
        enabled = b;
    }

    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if(update.hasCallbackQuery()){
            callbackReceiver.getCallBackQueryData(update);
            if(callbackReceiver.getData().equals("off")){
                sendMessage.setText("Turning off pc");
                sendMessage.setChatId(String.valueOf(callbackReceiver.getMessage().getChatId()));
                try {
                    execute(sendMessage);
                    commandListener.offPc();
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }else if(callbackReceiver.getData().equals("youtube")){
                sendMessage.setText("Open a youtube");
                sendMessage.setChatId(String.valueOf(callbackReceiver.getMessage().getChatId()));
                try {
                    execute(sendMessage);
                    commandListener.openYoutube();
                }catch (TelegramApiException e){
                    throw new RuntimeException(e);
                }
            }
        }
        saveToUpdateReceiver(update);
        setKeyboards();
        sendMessage.setChatId(String.valueOf(updateReceiver.getMessage().getChatId()));
        if(updateReceiver.getMessage().getText().equals("/menu")){
            sendMenu(updateReceiver.getId(), "Menu", keyboardsListener.getKeyboardM1());

        }else if(!updateReceiver.getMessage().getText().startsWith("/")){
            sendText(updateReceiver.getId(), "Используйте команду /menu");
        }

        commandListener.saveData();
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
    public void saveToUpdateReceiver(Update update){
        updateReceiver.setMessage(update.getMessage());
        updateReceiver.setUser(update.getMessage().getFrom());
        updateReceiver.setId(update.getMessage().getFrom().getId());
    }
    public void setKeyboards(){
        keyboardsListener.setKeyboardM1(InlineKeyboardMarkup.builder().keyboardRow(List.of(keyboardsListener.getYoutube(), keyboardsListener.getOff())).build());
    }

}

