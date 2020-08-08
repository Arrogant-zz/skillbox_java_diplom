package main.controller;

import main.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGeneralController {
    @Autowired
    BlogInfo blogInfo;

    @GetMapping("/api/init")
    public BlogInfo init() {
        return blogInfo;
    }

}