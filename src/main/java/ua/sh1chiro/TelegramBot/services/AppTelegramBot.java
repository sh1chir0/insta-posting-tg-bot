package ua.sh1chiro.TelegramBot.services;


import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.sh1chiro.TelegramBot.config.BotConfig;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Slf4j
@Component
public class AppTelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public AppTelegramBot(BotConfig config) { this.config = config; }
    @Override
    public String getBotUsername() { return config.getBotName(); }
    @Override
    public String getBotToken() { return config.getToken(); }
    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();

            switch (messageText){
                case "/start":
                    startBot(chatId, memberName);
                    break;
                default: log.info("Unexpected message");
            }
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привіт, " + userName + "! Іді нахуй");

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }
}