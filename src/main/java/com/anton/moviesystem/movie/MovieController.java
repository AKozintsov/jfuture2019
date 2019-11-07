package com.anton.moviesystem.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public String getMovieCount() {
        String result = movieService.getLastThreeYearsReleases("US") + "\n" +
                movieService.getLastThreeYearsReleases("CH");
        log.info("Movies result - " + result);
        return result;
    }
}
