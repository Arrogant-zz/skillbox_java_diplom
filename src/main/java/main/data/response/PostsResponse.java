package main.data.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import main.model.Post;
import main.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostsResponse {
    private List<PostResponse> posts;

    public PostsResponse() {
        posts = new ArrayList<>();
    }

    public PostsResponse(List<PostResponse> posts) {
        this.posts = posts;
    }

    public void add(List<PostResponse> posts) {
        this.posts.addAll(posts);
    }

    @JsonGetter("count")
    public int count() {
        return posts.size();
    }
}
