package main.service;

import lombok.AllArgsConstructor;
import main.data.IVoteCount;
import main.data.request.ListPostRequest;
import main.data.response.type.PostInListPost;
import main.data.response.ListPostResponse;
import main.repository.CommentRepository;
import main.repository.PostRepository;
import main.repository.VoteRepository;
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
        System.out.println(request);
        List<PostInListPost> posts = new ArrayList<>();
        postRepository.findAll().forEach(p -> {
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
        return new ListPostResponse(posts);
    }
}
