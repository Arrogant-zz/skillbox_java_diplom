package main.repository;

import main.model.Captcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Integer> {
    long countBySecretCodeAndCode(String secretCode, String code);
    void deleteByTimeBefore(Instant time);
}
