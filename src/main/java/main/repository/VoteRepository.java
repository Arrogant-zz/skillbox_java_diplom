package main.repository;

import main.model.query.IVoteCount;
import main.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
    int countByPostIdAndValue(int postId, boolean value);

    @Query("SELECT v.value AS value, COUNT(1) AS count "
            + "FROM Vote AS v WHERE v.postId = ?1 GROUP BY v.value ORDER BY v.value")
    List<IVoteCount> countTotalVotesByPostId(int postId);
}
