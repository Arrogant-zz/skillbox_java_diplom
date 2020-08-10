package main.service;

import lombok.AllArgsConstructor;
import main.data.response.InitResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitService {
    private InitResponse initResponse;

    public InitResponse response() {
        return initResponse;
    }
}
