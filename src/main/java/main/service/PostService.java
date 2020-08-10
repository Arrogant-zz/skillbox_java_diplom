package main.service;

import lombok.AllArgsConstructor;
import main.data.request.PostRequest;
import main.data.response.PostResponse;
import main.data.response.PostsResponse;
import main.model.Post;
import main.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;

    public PostsResponse response(PostRequest request) {
        System.out.println(request);
        List<PostResponse> posts = new ArrayList<>();
        postRepository.findAll().forEach(p -> posts.add(new PostResponse(p)));
        return new PostsResponse(posts);
    }
}
