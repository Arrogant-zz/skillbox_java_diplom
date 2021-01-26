package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.LoginRequest;
import main.data.request.RegisterRequest;
import main.data.response.*;
import main.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ApiAuthController {
    private final AuthService authService;

    @GetMapping("/api/auth/check")
    public ResponseEntity<AuthCheckResponse> check() {
        return ResponseEntity.ok(authService.check());
    }

    @GetMapping("/api/auth/captcha")
    public ResponseEntity<CaptchaResponse> captcha() throws IOException {
        return ResponseEntity.ok(authService.captcha());
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/api/auth/logout")
    public ResponseEntity<ResultResponse> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
