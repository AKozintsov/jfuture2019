package com.anton.moviesystem.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DirectorController {

    private final ListDirectorsService directorsService;

    @GetMapping("/directors")
    public List<String> getDirectors() {
        List<String> topDirectors = directorsService.getTopDirectors();
        log.info("Directors result - " + topDirectors);
        return topDirectors;
    }
}
