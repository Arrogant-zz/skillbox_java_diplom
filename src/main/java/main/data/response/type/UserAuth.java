package main.data.response.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserAuth {
    private int id;
    private String name;
    private String photo;
    private String email;
    @JsonProperty("moderation")
    private boolean hasModeration;
    private int moderationCount;
    @JsonProperty("settings")
    private boolean hasSettings;
}
