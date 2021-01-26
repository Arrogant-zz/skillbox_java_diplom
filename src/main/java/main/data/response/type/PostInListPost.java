package main.data.response.type;

import lombok.Data;
import main.core.TextUtilities;
import main.model.Post;

import java.sql.Timestamp;

@Data
public class PostInListPost {
    private int id;
    private long timestamp;
    private String title;
    private String announce;
    private UserInListPost user;
    private int viewCount;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;

    public PostInListPost(Post post) {
        String textFiltered = TextUtilities.html2text(post.getText());

        this.id = post.getId();
        this.title = post.getTitle();
        this.announce = textFiltered.substring(0, Math.min(255, textFiltered.length()));
        this.user = new UserInListPost(post.getAuthor().getId(), post.getAuthor().getName());
        this.viewCount = post.getViewCount();
        this.timestamp = post.getTime().getEpochSecond();
    }
}
