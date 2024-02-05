package ua.sh1chiro.TelegramBot.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}") String botName;
    @Value("${bot.token}") String token;
    @Value("${bot.chatId}") String chatId;
}