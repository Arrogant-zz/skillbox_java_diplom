package main.controller;

import lombok.AllArgsConstructor;
import main.data.request.CalendarRequest;
import main.data.request.ListTagRequest;
import main.data.response.*;
import main.service.CalendarService;
import main.service.InitService;
import main.service.StatisticService;
import main.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ApiGeneralController {
    private final InitService initService;
    private final TagService tagService;
    private final CalendarService calendarService;
    private final StatisticService statisticService;

    @GetMapping("/api/init")
    public ResponseEntity<InitResponse> init() {
        return ResponseEntity.ok(initService.init());
    }

    @GetMapping("/api/settings")
    public ResponseEntity<SettingResponse> settings() {
        return ResponseEntity.ok(initService.settings());
    }

    @GetMapping("/api/tag")
    public ResponseEntity<ListTagResponse> tag(ListTagRequest request) {
        return ResponseEntity.ok(tagService.response(request));
    }

    @GetMapping("/api/calendar")
    public ResponseEntity<CalendarResponse> calendar(CalendarRequest request) {
        return ResponseEntity.ok(calendarService.response(request));
    }

    @GetMapping("/api/statistics/my")
    public ResponseEntity<StatisticResponse> myStat() {
        return ResponseEntity.ok(statisticService.getMy());
    }

    @GetMapping("/api/statistics/all")
    public ResponseEntity<StatisticResponse> allStat() {
        return ResponseEntity.ok(statisticService.getAll());
    }
}
