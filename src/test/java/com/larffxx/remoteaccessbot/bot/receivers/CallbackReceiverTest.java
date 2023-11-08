package com.larffxx.remoteaccessbot.bot.receivers;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.*;

class CallbackReceiverTest {
    Update update = new Update();
    public void setUpdate(){
        update.setMessage(new Message());
        update.setCallbackQuery(new CallbackQuery());
    }
    @Test
    void twoPlusTwoShouldEqualFour(){
        CallbackReceiver callbackReceiver = new CallbackReceiver();
        assertEquals(4, callbackReceiver.add(2, 2));
    }
    @Test
    void getCallBackQueryData(){
        setUpdate();
        CallbackQuery callbackQuery = update.getCallbackQuery();
        CallbackReceiver callbackReceiver = new CallbackReceiver();
        assertEquals(callbackReceiver.getData(), callbackQuery.getData());
        assertEquals(callbackReceiver.getMessage(), callbackQuery.getMessage());
        assertEquals(callbackReceiver.getUser(), callbackQuery.getFrom());
    }
}