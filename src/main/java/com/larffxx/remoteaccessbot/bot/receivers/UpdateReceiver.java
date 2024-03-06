package com.larffxx.remoteaccessbot.bot.receivers;

import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardsListener;
import com.larffxx.remoteaccessbot.models.TextData;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateReceiver {
    final
    KeyboardsListener keyboardsListener;
    private final TextDataRepository textDataRepository;
    private Long pcId;
    private Update update;

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

    public Long getPcId() {
        return pcId;
    }

    public void setPcId(Long pcId) {
        this.pcId = pcId;
    }
}
