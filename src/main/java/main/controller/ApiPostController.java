package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.LikeRequest;
import main.data.request.ListPostRequest;
import main.data.response.ListPostResponse;
import main.data.response.ResultResponse;
import main.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ApiPostController {
    private PostService postService;

    @GetMapping(value = {"/api/post", "/api/post/search", "/api/post/byDate", "/api/post/byTag"})
    public ResponseEntity<ListPostResponse> get(ListPostRequest request) {
        return ResponseEntity.ok(postService.response(request));
    }

    @PostMapping("/api/post/like")
    public ResponseEntity<ResultResponse> like(@RequestBody LikeRequest request) {
        return ResponseEntity.ok(postService.like(true, request.getPostId()));
    }

    @PostMapping("/api/post/dislike")
    public ResponseEntity<ResultResponse> dislike(@RequestBody LikeRequest request) {
        return ResponseEntity.ok(postService.like(false, request.getPostId()));
    }
}
