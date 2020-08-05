package main.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_moderator", nullable = false, columnDefinition = "TINYINT")
    private boolean isModerator = false;

    @Column(name = "reg_time", nullable = false)
    private Instant regTime;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    private String code;

    @Column(name = "photo", columnDefinition = "text")
    private String photoURL;
}
