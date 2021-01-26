package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.ListPostRequest;
import main.data.response.ListPostResponse;
import main.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ApiPostController {
    private PostService postService;

    @GetMapping(value = {"/api/post", "/api/post/search"})
    public ResponseEntity<ListPostResponse> get(ListPostRequest request) {
        return ResponseEntity.ok(postService.response(request));
    }
}
