package ua.sh1chiro.TelegramBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.io.InputStream;

/**
 * Created by Sh1chiro on 06.02.2024.
 *
 * @author Sh1chiro
 */

@Entity
@Data
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Transient
    private String video;
}
