package main.repository;

import com.sun.istack.NotNull;
import main.model.ModerationStatus;
import main.model.Post;
import main.model.query.IPostCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    long countByModerationStatus(ModerationStatus status);

    @Query(
            value = "SELECT DISTINCT YEAR(time) AS year FROM posts",
            nativeQuery = true
    )
    List<Integer> getAllYears();

    @Query(
            value = "SELECT DATE(time) AS date, COUNT(1) as count FROM posts WHERE YEAR(time) = ?1 GROUP BY DATE(time)",
            nativeQuery = true
    )
    List<IPostCount> getCountByYear(int year);

    @Query(
            value = "SELECT COUNT(1) AS count FROM posts " +
                    "WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND time <= NOW() " +
                    "      AND (UPPER(title) LIKE UPPER(CONCAT('%', ?1, '%')) OR UPPER(text) LIKE UPPER(CONCAT('%', ?1, '%')))",
            nativeQuery = true
    )
    long countPostSearch(@NotNull String search);
}

