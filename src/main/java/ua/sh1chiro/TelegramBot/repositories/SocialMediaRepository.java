package ua.sh1chiro.TelegramBot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sh1chiro.TelegramBot.model.SocialMedia;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
}
