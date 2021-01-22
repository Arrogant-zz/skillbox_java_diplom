package main.service;

import lombok.AllArgsConstructor;
import main.data.request.ListPostRequest;
import main.data.request.ListTagRequest;
import main.data.response.ListPostResponse;
import main.data.response.ListTagResponse;
import main.data.response.type.PostInListPost;
import main.data.response.type.TagInListTag;
import main.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    private TagRepository tagRepository;

    public ListTagResponse response(ListTagRequest request) {
        List<TagInListTag> tags = new ArrayList<>();
        tagRepository.findAll().forEach(t -> tags.add(new TagInListTag(t)));
        return new ListTagResponse(tags);
    }
}
