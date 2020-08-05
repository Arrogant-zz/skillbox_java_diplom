package main.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "captcha_codes")
@Data
public class Captcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Instant time;

    @Column(columnDefinition = "tinytext", nullable = false)
    private String code;

    @Column(columnDefinition = "tinytext", nullable = false)
    private String secretCode;
}
