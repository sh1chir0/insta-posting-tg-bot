package ua.sh1chiro.TelegramBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.sh1chiro.TelegramBot.helpers.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "chatId")
    private Long chatId;
    @Column(name = "password")
    private String password;
    @Column(name = "access")
    private boolean access;
    @Column(name = "status")
    private UserStatus status;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<SocialMedia> socialMedias = new ArrayList<>();
    @OneToOne(cascade = CascadeType.REMOVE)
    private SocialMedia tempSocialMedia;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Post tempPost;

    @Column(name = "dateOfCreated")
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
