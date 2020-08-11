package main.controller;

import lombok.AllArgsConstructor;
import main.data.response.AuthCheckResponse;
import main.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ApiAuthController {
    private AuthService authService;

    @GetMapping("/api/auth/check")
    public ResponseEntity<AuthCheckResponse> init() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authService.check(!(authentication instanceof AnonymousAuthenticationToken)));
    }

}