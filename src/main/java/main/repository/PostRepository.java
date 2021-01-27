package main.repository;

import com.sun.istack.NotNull;
import main.model.ModerationStatus;
import main.model.Post;
import main.model.query.IPostCount;
import main.model.query.IStatistic;
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
            value = "SELECT COUNT(1) AS count FROM posts p " +
                    "LEFT JOIN tag2post t2p ON p.id = t2p.post_id AND ?3 IS NOT NULL " +
                    "LEFT JOIN tags t ON t2p.tag_id = t.id AND ?3 IS NOT NULL " +
                    "WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND time <= NOW() " +
                    "      AND (UPPER(title) LIKE UPPER(CONCAT('%', ?1, '%')) OR UPPER(text) LIKE UPPER(CONCAT('%', ?1, '%'))) " +
                    "      AND (DATE(p.time) = ?2 OR ?2 IS NULL) " +
                    "      AND (t.name = ?3 OR ?3 IS NULL)",
            nativeQuery = true
    )
    long countPostSearch(@NotNull String search, String byDate, String tag);

    @Query(
            value = "SELECT p_stat.postsCount, " +
                    "       v_stat.likesCount, " +
                    "       v_stat.dislikesCount, " +
                    "       p_stat.viewsCount, " +
                    "       p_stat.firstPublication " +
                    "FROM (SELECT COUNT(p.id) AS postsCount, " +
                    "             IFNULL(SUM(p.view_count), 0) AS viewsCount, " +
                    "             MIN(p.time) AS firstPublication " +
                    "      FROM posts p " +
                    "      WHERE p.user_id = :userId OR :userId = 0 " +
                    "     ) p_stat, " +
                    "     (SELECT IFNULL(SUM(pv.value), 0) AS likesCount, " +
                    "             IFNULL(COUNT(pv.id) - SUM(pv.value), 0) AS dislikesCount " +
                    "      FROM post_votes pv, posts p " +
                    "      WHERE pv.post_id = p.id AND (p.user_id = :userId OR :userId = 0) " +
                    "     ) v_stat",
            nativeQuery = true
    )
    IStatistic getStatistic(int userId);
}

