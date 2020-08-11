package main.service;

import main.data.response.AuthCheckResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthCheckResponse check(boolean isAuthenticated) {
        AuthCheckResponse authCheckResponse = new AuthCheckResponse(isAuthenticated);
        return authCheckResponse;
    }
}
