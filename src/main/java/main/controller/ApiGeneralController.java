package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.ListTagRequest;
import main.data.response.InitResponse;
import main.data.response.ListTagResponse;
import main.data.response.SettingResponse;
import main.service.InitService;
import main.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ApiGeneralController {
    private InitService initService;
    private TagService tagService;

    @GetMapping("/api/init")
    public ResponseEntity<InitResponse> init() {
        return ResponseEntity.ok(initService.init());
    }

    @GetMapping("/api/settings")
    public ResponseEntity<SettingResponse> settings() {
        return ResponseEntity.ok(initService.settings());
    }

    @GetMapping("/api/tag")
    public ResponseEntity<ListTagResponse> tag(ListTagRequest request) {
        return ResponseEntity.ok(tagService.response(request));
    }
}