package main.service;

import lombok.AllArgsConstructor;
import main.data.request.CalendarRequest;
import main.data.response.CalendarResponse;
import main.model.query.IPostCount;
import main.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalendarService {
    private final PostRepository postRepository;

    public CalendarResponse response(CalendarRequest request) {
        List<Integer> years = postRepository.getAllYear();
        List<IPostCount> posts = postRepository.getCountByYear(request.getYear());

        return new CalendarResponse(
                years,
                posts.stream().collect(Collectors.toMap(IPostCount::getDate, IPostCount::getCount))
        );
    }
}
