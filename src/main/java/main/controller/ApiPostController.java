package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.PostRequest;
import main.data.response.PostsResponse;
import main.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ApiPostController {
    private PostService postService;

    @GetMapping("/api/post")
    public PostsResponse get(PostRequest request) {
        return postService.response(request);
    }
}