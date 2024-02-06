package ua.sh1chiro.TelegramBot.helpers;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/addinstagram", "add instagram"),
            new BotCommand("/addtiktok", "add tik tok"),
            new BotCommand("/addyoutube", "add youtube")
    );

    String HELP_TEXT = "This bot will help to count the number of messages in the chat. " +
            "The following commands are available to you:\n\n" +
            "/start - start the bot\n" +
            "/help - help menu";
}