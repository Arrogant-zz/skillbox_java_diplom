package main.service;

import com.github.cage.Cage;
import com.github.cage.image.Painter;
import lombok.RequiredArgsConstructor;
import main.data.request.LoginRequest;
import main.data.request.RegisterRequest;
import main.data.response.*;
import main.model.Captcha;
import main.model.User;
import main.repository.CaptchaRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CaptchaRepository captchaRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${app.param.captcha.height}")
    private int captchaHeight;
    @Value("${app.param.captcha.width}")
    private int captchaWidth;
    @Value("${app.param.captcha.deletePeriod}")
    private long captchaDeletePeriod;

    public AuthCheckResponse check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthCheckResponse authCheckResponse = new AuthCheckResponse(!(authentication instanceof AnonymousAuthenticationToken));
        return authCheckResponse;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse();
    }

    public LogoutResponse logout() {
        SecurityContextHolder.clearContext();

        return new LogoutResponse();
    }

    public RegisterResponse register(RegisterRequest request) {
        Map<String, String> errors = new HashMap<>();

        //Check Errors
        checkName(request.getName(), errors);
        checkCaptcha(request.getCaptchaSecret(), request.getCaptcha(), errors);
        checkEmail(request.getEmail(), errors);
        checkPassword(request.getPassword(), errors);

        if (errors.size() > 0) {
            return new RegisterResponse(false, errors);
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .regTime(Instant.now())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return new RegisterResponse();
    }

    @Transactional
    public CaptchaResponse captcha() throws IOException {
        captchaRepository.deleteByTimeBefore(Instant.now().minusSeconds(captchaDeletePeriod));

        Painter painter = new Painter(captchaWidth, captchaHeight, null, null, null, new Random());
        Cage cage = new Cage(painter, null, null, null, Cage.DEFAULT_COMPRESS_RATIO, null, null);

        String code = cage.getTokenGenerator().next().substring(0, 7);
        String secret = cage.getTokenGenerator().next();

        Captcha captcha = Captcha.builder()
                .code(code)
                .secretCode(secret)
                .time(Instant.now())
                .build();

        captchaRepository.save(captcha);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(cage.drawImage(code), "png", baos);
        return new CaptchaResponse(
                secret,
                "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray())
        );
    }

    private void checkPassword(String password, Map<String, String> errors) {
        if (password == null || password.length() < 6) {
            errors.put("password", "Пароль короче 6-ти символов");
        }
    }

    private void checkEmail(String email, Map<String, String> errors) {
        if (userRepository.countByEmail(email) > 0) {
            errors.put("email", "Этот e-mail уже зарегистрирован");
        }
    }

    private void checkName(String name, Map<String, String> errors) {
        if (name == null || name.isEmpty()) {
            errors.put("name", "Имя указано неверно");
        }
    }

    private void checkCaptcha(String secret, String code, Map<String, String> errors) {
        if (captchaRepository.countBySecretCodeAndCode(secret, code) == 0) {
            errors.put("captcha", "Код с картинки введён неверно");
        }
    }
}
