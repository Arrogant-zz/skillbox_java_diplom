package main.service;

import lombok.AllArgsConstructor;
import main.data.request.ListPostRequest;
import main.data.response.ListPostResponse;
import main.data.response.type.PostInListPost;
import main.model.ModerationStatus;
import main.model.Post;
import main.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final EntityManager entityManager;

    public ListPostResponse response(ListPostRequest request) {
        String search = Optional.ofNullable(request.getQuery()).orElse("");

        List<PostInListPost> posts = entityManager
                .createNamedQuery("PostWithStat", Tuple.class)
                .setParameter("offset", request.getOffset())
                .setParameter("limit", request.getLimit())
                .setParameter("sortType", request.getMode())
                .setParameter("search", search)
                .getResultStream()
                .map(p -> {
                    PostInListPost post = new PostInListPost((Post) p.get(0));
                    post.setCommentCount((int) p.get(1));
                    post.setLikeCount((int) p.get(2));
                    post.setDislikeCount((int) p.get(3));
                    return post;
                })
                .collect(Collectors.toList());

        ListPostResponse listPostResponse = new ListPostResponse(posts);
        listPostResponse.setCount(postRepository.countPostSearch(search));

        return listPostResponse;
    }

    public long countPostInModeration() {
        return postRepository.countByModerationStatus(ModerationStatus.NEW);
    }
}
