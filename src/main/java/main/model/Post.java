package main.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NamedNativeQuery(
        name = "PostWithStat",
        query = "SELECT p.*, " +
                "COUNT(com.id) AS comment_count, " +
                "IFNULL(SUM(v.value), 0) AS like_count, " +
                "IFNULL(COUNT(v.value) - SUM(v.value), 0) AS dislike_count " +
                "FROM blog.posts p " +
                "LEFT JOIN blog.post_comments com ON com.post_id = p.id " +
                "LEFT JOIN blog.post_votes v ON v.post_id = p.id " +
                "WHERE p.is_active = 1 AND p.moderation_status = 'ACCEPTED' AND p.time <= NOW() " +
                "      AND (UPPER(p.title) LIKE UPPER(CONCAT('%', :search, '%')) OR UPPER(p.text) LIKE UPPER(CONCAT('%', :search, '%'))) " +
                "GROUP BY p.id " +
                "ORDER BY " +
                "CASE WHEN :sortType = 'recent' THEN p.time " +
                "     WHEN :sortType = 'popular' THEN comment_count " +
                "     WHEN :sortType = 'best' THEN like_count " +
                "     ELSE p.time " +
                "END DESC, " +
                "CASE WHEN :sortType = 'early' THEN p.time " +
                "END " +
                "LIMIT :offset, :limit",
        resultSetMapping = "PostWithStatisticMapping"
)
@SqlResultSetMapping(
        name = "PostWithStatisticMapping",
        entities = @EntityResult(
                entityClass = Post.class
        ),
        columns = {
                @ColumnResult(name = "comment_count", type = int.class),
                @ColumnResult(name = "like_count", type = int.class),
                @ColumnResult(name = "dislike_count", type = int.class),
        }
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT")
    private boolean isActive = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", referencedColumnName = "id")
    private User moderator;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User author;

    @Column(nullable = false)
    private Instant time;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String text;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tag2post",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('NEW', 'ACCEPTED', 'DECLINED')", nullable = false)
    private ModerationStatus moderationStatus;
}
