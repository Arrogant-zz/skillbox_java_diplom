package main.model.query;

import java.time.Instant;

public interface IStatistic {
    int getPostsCount();
    int getLikesCount();
    int getDislikesCount();
    int getViewsCount();
    Instant getFirstPublication();
}
