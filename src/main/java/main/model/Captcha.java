package main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "captcha_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
