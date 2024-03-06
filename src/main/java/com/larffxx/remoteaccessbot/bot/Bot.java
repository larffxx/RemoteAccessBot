package com.larffxx.remoteaccessbot.bot;

import com.larffxx.remoteaccessbot.bot.exceptions.NullMessageException;
import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.listeners.buttons.Button;
import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardSetup;
import com.larffxx.remoteaccessbot.bot.listeners.preprocessors.ButtonPreProcessor;
import com.larffxx.remoteaccessbot.bot.listeners.preprocessors.CommandPreProcessor;
import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.listeners.commands.Command;
import com.larffxx.remoteaccessbot.bot.receivers.CallbackReceiver;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;


@Component
@Getter
public class Bot extends TelegramLongPollingBot {
    private KeyboardSetup keyboardSetup;
    private ButtonPreProcessor buttonPreProcessor;
    private CommandPreProcessor commandPreProcessor;
    private
    CallbackReceiver callbackReceiver;
    private
    CommandListener commandListener;
    private
    KeyboardsListener keyboardsListener;
    private TextDataRepository textDataRepository;
    @Value("${name}")
    private String botUsername;
    @Value("${token}")
    private String botToken;
    protected UpdateReceiver updateReceiver;

    @Autowired
    public Bot(CallbackReceiver callbackReceiver, CommandListener commandListener, KeyboardsListener keyboardsListener, TextDataRepository textDataRepository, UpdateReceiver updateReceiver, CommandPreProcessor commandPreProcessor, ButtonPreProcessor buttonPreProcessor, KeyboardSetup keyboardSetup) {
        this.callbackReceiver = callbackReceiver;
        this.commandListener = commandListener;
        this.keyboardsListener = keyboardsListener;
        this.textDataRepository = textDataRepository;
        this.updateReceiver = updateReceiver;
        this.commandPreProcessor = commandPreProcessor;
        this.buttonPreProcessor = buttonPreProcessor;
        this.keyboardSetup = keyboardSetup;
    }

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
        commandListener.saveToUpdateReceiver(update);
        Command command = null;
        Button button;
        keyboardSetup.setKeyboards();
        try {
            if(update.getMessage().getText() != null){
                commandListener.setData(update);
            }
        }catch (NullPointerException ignored){}
        try {
            if (updateReceiver.getUpdate().getMessage().getText() != null) {
                command = commandPreProcessor.command(updateReceiver.getUpdate().getMessage().getText());
            }
        }catch (NullPointerException e){
            command = commandPreProcessor.command("123");
        }
        if(update.hasCallbackQuery()){
            button = buttonPreProcessor.button(updateReceiver.getUpdate().getCallbackQuery().getData());
            try {
                if(button.getButton().equals("screenshot")){
                    execute((SendPhoto) button.clicked(updateReceiver));
                }else execute((BotApiMethod <? extends  Serializable>)button.clicked(updateReceiver));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (command != null) {
            command.getCommand();
            try {
                execute((BotApiMethod<? extends Serializable>) command.execute(updateReceiver));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
