package main.service;

import lombok.AllArgsConstructor;
import main.core.ContextUtilities;
import main.data.response.StatisticResponse;
import main.model.query.IStatistic;
import main.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticService {
    private final PostRepository postRepository;

    public StatisticResponse getMy() {
        IStatistic statistic = postRepository.getStatistic(ContextUtilities.getCurrentUserId());

        return new StatisticResponse(statistic);
    }

    public StatisticResponse getAll() {
        IStatistic statistic = postRepository.getStatistic(0);

        return new StatisticResponse(statistic);
    }
}
