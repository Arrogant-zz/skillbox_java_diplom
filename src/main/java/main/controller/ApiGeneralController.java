package main.controller;

import lombok.AllArgsConstructor;
import main.data.response.InitResponse;
import main.service.InitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ApiGeneralController {
    private InitService initService;

    @GetMapping("/api/init")
    public InitResponse init() {
        return initService.response();
    }

}