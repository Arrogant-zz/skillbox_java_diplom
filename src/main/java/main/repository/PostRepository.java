package main.repository;

import main.model.ModerationStatus;
import main.model.Post;
import main.model.query.IPostCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);

    long countByModerationStatus(ModerationStatus status);

    @Query(
            value = "SELECT DISTINCT YEAR(time) AS year FROM posts",
            nativeQuery = true
    )
    List<Integer> getAllYear();

    @Query(
            value = "SELECT DATE(time) AS date, COUNT(1) as count FROM posts WHERE YEAR(time) = ?1 GROUP BY DATE(time)",
            nativeQuery = true
    )
    List<IPostCount> getCountByYear(int year);
}

