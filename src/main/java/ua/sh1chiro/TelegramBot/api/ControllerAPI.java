package ua.sh1chiro.TelegramBot.api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.sh1chiro.TelegramBot.model.Post;
import ua.sh1chiro.TelegramBot.model.SocialMedia;
import ua.sh1chiro.TelegramBot.model.User;

import java.io.File;
import java.util.List;

/**
 * Created by Sh1chiro on 06.02.2024.
 *
 * @author Sh1chiro
 */

public class ControllerAPI {
    public static String createPost(Post post, User user){
        List<SocialMedia> socialMedias = user.getSocialMedias();
        for(SocialMedia socialMedia: socialMedias){
            switch (socialMedia.getSocialMediaType()){
                case TIK_TOK:
                    TikTokAPI.post(post, socialMedia.getLogin(), socialMedia.getPassword());
                    break;
                case INSTAGRAM:
                    InstagramAPI.post(post, socialMedia.getLogin(), socialMedia.getPassword());
                    break;
                case YOUTUBE:
                    YoutubeAPI.post(post, socialMedia.getLogin(), socialMedia.getPassword());
                    break;
            }
        }

        File fileToDelete = new File(post.getVideo());
        fileToDelete.delete();


        return "a";
    }
}
