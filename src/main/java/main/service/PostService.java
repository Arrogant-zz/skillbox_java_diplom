package main.service;

import lombok.AllArgsConstructor;
import main.core.OffsetPageRequest;
import main.model.query.IVoteCount;
import main.data.request.ListPostRequest;
import main.data.response.type.PostInListPost;
import main.data.response.ListPostResponse;
import main.model.ModerationStatus;
import main.model.Post;
import main.repository.CommentRepository;
import main.repository.PostRepository;
import main.repository.VoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private VoteRepository voteRepository;
    private CommentRepository commentRepository;

    public ListPostResponse response(ListPostRequest request) {
        Sort sort = null;

        switch(request.getMode()) {
            case "recent":
                sort = Sort.by("time").descending();
                break;
            case "popular":
            case "best":
                break;
            case "early":
                sort = Sort.by("time").ascending();
                break;
            default:
                sort = Sort.by("id").descending();
                break;
        }

        OffsetPageRequest pageable = new OffsetPageRequest(request.getOffset(), request.getLimit(), sort);

        List<PostInListPost> posts = new ArrayList<>();
        Page<Post> page = postRepository.findAll(pageable);

        page.forEach(p -> {
            PostInListPost post = new PostInListPost(p);

            List<IVoteCount> votes = voteRepository.countTotalVotesByPostId(post.getId());
            for (IVoteCount vote : votes) {
                if (vote.getValue()) {
                    post.setLikeCount(vote.getCount());
                } else {
                    post.setDislikeCount(vote.getCount());
                }
            }

            post.setCommentCount(commentRepository.countByPostId(post.getId()));

            posts.add(post);
        });

        ListPostResponse listPostResponse = new ListPostResponse(posts);
        listPostResponse.setCount(page.getTotalElements());

        return listPostResponse;
    }

    public long countPostInModeration() {
        return postRepository.countByModerationStatus(ModerationStatus.NEW);
    }
}
