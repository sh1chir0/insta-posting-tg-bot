package ua.sh1chiro.TelegramBot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sh1chiro.TelegramBot.model.Post;

/**
 * Created by Sh1chiro on 06.02.2024.
 *
 * @author Sh1chiro
 */

public interface PostRepository extends JpaRepository<Post, Long> {
}
