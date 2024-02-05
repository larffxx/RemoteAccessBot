package com.larffxx.remoteaccessbot.bot.receivers;

import com.larffxx.remoteaccessbot.models.TextData;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Component
public class UpdateReceiver {
    @Autowired
    private TextDataRepository textDataRepository;
    private Update update;
    private Long id;
    private Message message;
    private User user;
    @Autowired
    public UpdateReceiver(){}

    public void setId(Long id){
        this.id = id;
    }
    public void setMessage(Message message){
        this.message = message;
    }
    public void setUser(User user){
        this.user = user;
    }
    public long getId(){
        return id;
    }
    public Message getMessage(){
        return message;
    }
    public User getUser(){
        return user;
    }

    public void saveData(String userName, String message){
        TextData textData = new TextData(userName, message);
        textDataRepository.save(textData);

    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
