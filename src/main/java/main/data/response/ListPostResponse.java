package main.data.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import main.data.response.type.PostInListPost;

import java.util.List;

@Data
public class ListPostResponse {
    private List<PostInListPost> posts;

    public ListPostResponse(List<PostInListPost> posts) {
        this.posts = posts;
    }

    public void add(List<PostInListPost> posts) {
        this.posts.addAll(posts);
    }

    @JsonGetter("count")
    public int count() {
        return posts.size();
    }
}
