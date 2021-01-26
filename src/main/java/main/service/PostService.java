package main.service;

import lombok.AllArgsConstructor;
import main.core.ContextUtilities;
import main.data.request.ListPostRequest;
import main.data.response.ListPostResponse;
import main.data.response.ResultResponse;
import main.data.response.type.PostInListPost;
import main.model.ModerationStatus;
import main.model.Post;
import main.model.Vote;
import main.repository.PostRepository;
import main.repository.VoteRepository;
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
    private final VoteRepository voteRepository;

    public ListPostResponse response(ListPostRequest request) {
        String search = Optional.ofNullable(request.getQuery()).orElse("");
        String mode = Optional.ofNullable(request.getMode()).orElse("recent");
        String byDate = request.getDate();
        String tag = request.getTag();

        List<PostInListPost> posts = entityManager
                .createNamedQuery("PostWithStat", Tuple.class)
                .setParameter("offset", request.getOffset())
                .setParameter("limit", request.getLimit())
                .setParameter("sortType", mode)
                .setParameter("search", search)
                .setParameter("byDate", byDate)
                .setParameter("tag", tag)
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
        listPostResponse.setCount(postRepository.countPostSearch(search, byDate, tag));

        return listPostResponse;
    }

    public ResultResponse like(boolean value, int postId) {
        Vote vote = voteRepository.findByPostIdAndUserId(
                postId,
                ContextUtilities.getCurrentUserId()
        ).orElse(
                Vote.builder()
                        .postId(postId)
                        .userId(ContextUtilities.getCurrentUserId())
                    .build()
        );

        ResultResponse result = new ResultResponse(vote.getId() == 0 || vote.isValue() != value);

        vote.setTime(Instant.now());
        vote.setValue(value);

        System.out.println(vote);

        voteRepository.save(vote);
        return result;
    }

    public long countPostInModeration() {
        return postRepository.countByModerationStatus(ModerationStatus.NEW);
    }
}
