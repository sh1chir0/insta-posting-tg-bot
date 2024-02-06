package ua.sh1chiro.TelegramBot.helpers.enums;

import lombok.Getter;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Getter
public enum SocialMediaType {
    INSTAGRAM("Instagram"), TIK_TOK("Tik Tok"), YOUTUBE("Youtube");

    private String name;

    SocialMediaType(String name) {
        this.name = name;
    }
}