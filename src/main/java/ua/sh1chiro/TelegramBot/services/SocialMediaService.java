package ua.sh1chiro.TelegramBot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.sh1chiro.TelegramBot.repositories.SocialMediaRepository;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;
}
