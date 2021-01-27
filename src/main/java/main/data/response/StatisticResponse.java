package main.data.response;

import lombok.Data;
import main.model.query.IStatistic;

@Data
public class StatisticResponse {
    private int postsCount;
    private int likesCount;
    private int dislikesCount;
    private int viewsCount;
    private Long firstPublication;

    public StatisticResponse(IStatistic statistic) {
        postsCount = statistic.getPostsCount();
        likesCount = statistic.getLikesCount();
        dislikesCount = statistic.getDislikesCount();
        viewsCount = statistic.getViewsCount();
        firstPublication =
                (statistic.getFirstPublication() != null)
                ? statistic.getFirstPublication().getEpochSecond()
                : null;
    }
}
