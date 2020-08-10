package main.data.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.model.Post;
import main.model.User;

@Data
public class PostResponse {
    private int id;
    private int timestamp;
    private String title;
    @JsonIgnore
    private String text;
    private UserPostResponse user;
    private int viewCount;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.user = new UserPostResponse(post.getAuthor().getId(), post.getAuthor().getName());
        this.viewCount = post.getViewCount();
    }

    @JsonProperty("announce")
    public String getAnnounce() {
        return this.text;
    }
}
