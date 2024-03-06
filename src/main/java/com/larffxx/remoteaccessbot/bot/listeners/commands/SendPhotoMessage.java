package com.larffxx.remoteaccessbot.bot.listeners.commands;

import com.larffxx.remoteaccessbot.bot.listeners.CommandListener;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

@Component
@AllArgsConstructor
public class SendPhotoMessage implements Command<SendPhoto> {
    CommandListener commandListener;
    @Override
    public SendPhoto execute(UpdateReceiver update) {
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(String.valueOf(update.getPcId()))
                .photo(new InputFile(new File("C:\\Users\\offic\\IdeaProjects\\RemoteAccessBot\\src\\main\\resources\\screenshots\\" + commandListener.getLastNum() + ".jpg")))
                .caption("Here is your screen")
                .parseMode(ParseMode.HTML)
                .build();

        return sendPhoto;
    }

    @Override
    public String getCommand() {
        return "/sendPhoto";
    }
}
