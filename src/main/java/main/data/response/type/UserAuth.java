package main.data.response.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.model.User;

@Data
public class UserAuth {
    private int id;
    private String name;
    private String photo;
    private String email;
    @JsonProperty("moderation")
    private boolean hasModeration;
    private long moderationCount;
    @JsonProperty("settings")
    private boolean hasSettings;

    public UserAuth(User user, long moderationCount) {
        id = user.getId();
        name = user.getName();
        photo = user.getPhotoURL();
        email = user.getEmail();
        hasModeration = user.isModerator();
        hasSettings = user.isModerator();
        this.moderationCount = moderationCount;
    }
}
