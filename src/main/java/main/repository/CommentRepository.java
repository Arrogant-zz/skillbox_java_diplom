package main.repository;

import main.data.IVoteCount;
import main.model.Comment;
import main.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    int countByPostId(int postId);
}