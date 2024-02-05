package ua.sh1chiro.TelegramBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.sh1chiro.TelegramBot.enums.SocialMediaType;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Data
@Entity
@Table(name = "socialMedia")
@AllArgsConstructor
@NoArgsConstructor
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "socialMediaType")
    private SocialMediaType socialMediaType;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}