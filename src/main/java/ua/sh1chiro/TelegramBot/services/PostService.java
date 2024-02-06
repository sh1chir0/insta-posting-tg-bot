package ua.sh1chiro.TelegramBot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.sh1chiro.TelegramBot.model.Post;
import ua.sh1chiro.TelegramBot.repositories.PostRepository;

/**
 * Created by Sh1chiro on 06.02.2024.
 *
 * @author Sh1chiro
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public void remove(Post post){
        postRepository.delete(post);
    }
}
