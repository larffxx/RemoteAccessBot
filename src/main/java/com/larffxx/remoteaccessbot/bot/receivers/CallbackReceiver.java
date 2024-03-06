package com.larffxx.remoteaccessbot.bot.receivers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class CallbackReceiver {

    private CallbackQuery callbackQuery;

    private String data;
    private User user;
    private  Message message;

    private boolean hasData;
    private Update update;


    public void getCallBackQueryData(Update update){
        callbackQuery = update.getCallbackQuery();
        data = callbackQuery.getData();
        user = callbackQuery.getFrom();
        message = callbackQuery.getMessage();
        hasData = update.hasCallbackQuery();
        this.update = update;
    }

    public CallbackQuery getCallbackQuery(){
        return callbackQuery;
    }
    public CallbackReceiver(){}

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int add(int number1, int number2){
        return number1 + number2;
    }

    public boolean isHasData() {
        return hasData;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
