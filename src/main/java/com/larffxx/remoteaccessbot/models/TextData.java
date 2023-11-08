package com.larffxx.remoteaccessbot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TextData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName, text;

    public void setText(String text){
        this.text = text;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public String getText(){
        return text;
    }

    public TextData(String userName, String text){
        this.userName = userName;
        this.text = text;
    }
    public TextData(){}

}
