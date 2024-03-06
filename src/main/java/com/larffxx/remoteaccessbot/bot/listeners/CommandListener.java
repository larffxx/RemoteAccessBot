package com.larffxx.remoteaccessbot.bot.listeners;

import com.larffxx.remoteaccessbot.bot.listeners.keyboards.KeyboardsListener;
import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Component
@AllArgsConstructor
public class CommandListener {
    private final TextDataRepository textDataRepository;
    private UpdateReceiver updateReceiver;
    KeyboardsListener keyboardsListener;
    private static TextDataRepository repo;
    private static int lastNum;


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

    public static void takeScreenshot() {
        System.setProperty("java.awt.headless", "false");
        Random random = new Random();
        int tempNum = random.nextInt(1000);
        lastNum = tempNum;
        try {
//            // It saves screenshot to desired path
            String path = "C:\\Users\\offic\\IdeaProjects\\RemoteAccessBot\\src\\main\\resources\\screenshots\\" + tempNum + ".jpg";
            GraphicsDevice[] screens = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getScreenDevices();

            Rectangle allScreenBounds = new Rectangle();
            for (GraphicsDevice screen : screens) {
                Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
                allScreenBounds.width += screenBounds.width;
                allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            }

            BufferedImage capture = new Robot().createScreenCapture(allScreenBounds);
            File imageFile = new File(path);
            ImageIO.write(capture, "jpg", imageFile);
            assertTrue(imageFile.exists());
        } catch (AWTException | IOException ex) {
            System.out.println(ex);
        }

    }

    public int getLastNum() {
        return lastNum;
    }

    public String getDataFromDB() {
        List<Tuple> users = textDataRepository.findByUserName(updateReceiver.getUpdate().getCallbackQuery().getFrom().getFirstName());
        return users.get(users.size() - 1).toString();
    }

    public void setData(Update update) {
        updateReceiver.saveData(update.getMessage().getFrom().getFirstName(), update.getMessage().getText());
        updateReceiver.setPcId(update.getMessage().getFrom().getId());
    }


    public void saveToUpdateReceiver(Update update) {
        updateReceiver.setUpdate(update);
    }
}

