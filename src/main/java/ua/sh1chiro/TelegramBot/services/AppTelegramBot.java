package ua.sh1chiro.TelegramBot.services;


import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.sh1chiro.TelegramBot.config.BotConfig;
import ua.sh1chiro.TelegramBot.helpers.enums.SocialMediaType;
import ua.sh1chiro.TelegramBot.helpers.enums.UserStatus;
import ua.sh1chiro.TelegramBot.model.SocialMedia;
import ua.sh1chiro.TelegramBot.model.User;

import static ua.sh1chiro.TelegramBot.helpers.BotCommands.HELP_TEXT;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Slf4j
@Component

public class AppTelegramBot extends TelegramLongPollingBot {
    final BotConfig config;
    private final UserService userService;
    private final SocialMediaService socialMediaService;

    public AppTelegramBot(BotConfig config, UserService userService, SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
        this.config = config;
        this.userService = userService;
    }
    @Override
    public String getBotUsername() { return config.getBotName(); }
    @Override
    public String getBotToken() { return config.getToken(); }
    @Override
    public void onUpdateReceived(@NotNull Update update) {
        long chatId;
        long userId;
        String userName;
        String receivedMessage;

        if(update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();

                User user = userService.getUserByChatId(chatId);

                if(user != null){
                    switch(user.getStatus()){
                        case UserStatus.WAITING_LOGIN:
                            setLogin(user, receivedMessage, chatId);
                            break;
                        case UserStatus.WAITING_PASSWORD:
                            setPassword(user, receivedMessage, chatId);
                            break;
                        case UserStatus.FREE:
                            botAnswerUtils(receivedMessage, chatId, userName);
                            break;
                    }
                }else{
                    botAnswerUtils(receivedMessage, chatId, userName);
                }
            }

        } else if (update.getMessage().hasVideo()) {
            Video video = update.getMessage().getVideo();
            String fileId = video.getFileId();


        }else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }
    }
    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage){
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId, HELP_TEXT);
                break;
            case "/addinstagram":
                addSocialMediaType(chatId, SocialMediaType.INSTAGRAM);
                break;
            case "/addtiktok":
                addSocialMediaType(chatId, SocialMediaType.TIK_TOK);
                break;
            case "/addyoutube":
                addSocialMediaType(chatId, SocialMediaType.YOUTUBE);
                break;
            case "/myaccounts":
                showUserAccounts(chatId);
                break;
            default: break;
        }
    }
    private void startBot(long chatId, String userName) {
        User user = userService.getUserByChatId(chatId);
        if(user == null){
            user = new User();
            user.setChatId(chatId);
            user.setNickname(userName);
            user.setStatus(UserStatus.FREE);

            user = userService.save(user);
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + userName + "! I`m server!");

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void addSocialMediaType(Long chatId, SocialMediaType socialMediaType){
        User user = userService.getUserByChatId(chatId);
        try{
            SocialMedia socialMedia = new SocialMedia();
            socialMedia.setSocialMediaType(socialMediaType);

            user.setTempSocialMedia(socialMediaService.save(socialMedia));

            sendMessage(chatId, "Enter " + socialMediaType.name().toLowerCase() + " login:" );

            user.setStatus(UserStatus.WAITING_LOGIN);

            userService.save(user);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void setLogin(User user, String login, Long chatId){
        user.getTempSocialMedia().setLogin(login.trim());
        socialMediaService.save(user.getTempSocialMedia());

        sendMessage(chatId, "Enter password:");

        user.setStatus(UserStatus.WAITING_PASSWORD);
        userService.save(user);
    }

    private void setPassword(User user, String password, Long chatId){
        user.getTempSocialMedia().setPassword(password.trim());
        SocialMedia socialMedia = socialMediaService.save(user.getTempSocialMedia());

        user.getSocialMedias().add(socialMedia);

        user.setStatus(UserStatus.FREE);
        user.setTempSocialMedia(null);
        userService.save(user);

        sendMessage(chatId,"Account has been added");
    }

    private void sendMessage(Long chatId, String text){
        SendMessage requestLoginMessage = new SendMessage();
        requestLoginMessage.setChatId(chatId);
        requestLoginMessage.setText(text);
        try {
            execute(requestLoginMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void showUserAccounts(Long chatId) {
        User user = userService.getUserByChatId(chatId);

        StringBuilder USER_ACCOUNTS = new StringBuilder("Your accounts: \n" +
                "--------------------------------:\n\n");

        int i = 0;
        for (SocialMedia socialMedia : user.getSocialMedias()) {
            USER_ACCOUNTS.append(i++).append(". ")
                    .append(socialMedia.getSocialMediaType().getName())
                    .append(": login - ").append(socialMedia.getLogin())
                    .append(" | password - ").append(socialMedia.getPassword())
                    .append("\n\n --------------------------------\n");
        }

        sendMessage(chatId, USER_ACCOUNTS.toString());
    }
}