package ua.sh1chiro.TelegramBot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.sh1chiro.TelegramBot.model.User;
import ua.sh1chiro.TelegramBot.repositories.UserRepository;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User getUserByChatId(Long id){
        return userRepository.getUserByChatId(id);
    }
}
