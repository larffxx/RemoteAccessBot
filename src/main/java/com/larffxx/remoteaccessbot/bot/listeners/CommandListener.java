package com.larffxx.remoteaccessbot.bot.listeners;

import com.larffxx.remoteaccessbot.bot.receivers.UpdateReceiver;
import com.larffxx.remoteaccessbot.models.TextData;
import com.larffxx.remoteaccessbot.repo.TextDataRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class CommandListener {
    @Autowired
    private TextDataRepository textDataRepository;
    @Autowired
    private static TextData textData;

    @Autowired
    private static UpdateReceiver updateReceiver;
    @Autowired
    private static TextDataRepository repo;

    private static int lastNum;
    private String lastMessageFromUser;


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
            Thread.sleep(120);
            Robot r = new Robot();

            // It saves screenshot to desired path
            String path = "C:\\Users\\offic\\IdeaProjects\\RemoteAccessBot\\src\\main\\resources\\screenshots\\" + tempNum + ".jpg";


            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path));
            System.out.println("Screenshot saved");
        } catch (AWTException | IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
    public int getLastNum() {
        return lastNum;
    }

    public String getDataFromDB(){
        List<Tuple> users = textDataRepository.findByUserName("Яков");
        return users.get(users.size() - 1).toString();

    }

    public String getLastMessageFromUser() {
        return lastMessageFromUser;
    }

    public void setLastMessageFromUser(String lastMessageFromUser) {
        this.lastMessageFromUser = lastMessageFromUser;
    }
}

