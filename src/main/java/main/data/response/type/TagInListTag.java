package main.data.response.type;

import lombok.Data;
import main.core.TextUtilities;
import main.model.Post;
import main.model.Tag;

@Data
public class TagInListTag {
    private String name;
    private double weight;

    public TagInListTag(Tag tag) {
        this.name = tag.getName();
        this.weight = 0.0;
    }
}
