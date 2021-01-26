package main.data.response;

import lombok.Data;
import main.data.response.type.PostInListPost;
import main.data.response.type.TagInListTag;

import java.util.List;

@Data
public class ListTagResponse {
    private List<TagInListTag> tags;

    public ListTagResponse(List<TagInListTag> tags) {
        this.tags = tags;
    }

    public void add(List<TagInListTag> tags) {
        this.tags.addAll(tags);
    }

}
