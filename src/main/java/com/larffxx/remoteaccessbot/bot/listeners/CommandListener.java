package com.larffxx.remoteaccessbot.bot.listeners;

import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandListener {

    @Autowired
    UpdateReceiver updateReceiver;


    public void offPc() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("shutdown -s -t 0");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openYoutube() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + "https://www.youtube.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveData() {
        updateReceiver.saveData(updateReceiver.getUser().getFirstName(), updateReceiver.getMessage().getText());
    }
}

