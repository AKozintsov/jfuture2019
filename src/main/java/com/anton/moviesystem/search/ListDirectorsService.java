package com.anton.moviesystem.search;

import com.anton.moviesystem.common.Movie;
import com.anton.moviesystem.common.MovieApiWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
class ListDirectorsService {

    private final RestTemplate restTemplate;

    private final SearchService searchService;

    @Value("${moviedb.movie.highrated}")
    private String highRatedUrl;

    @Value("${moviedb.apikey}")
    private String apiKey;

    List<String> getTopDirectors() {
        Set<String> directors = new HashSet<>();
        List<Movie> topHighRatedMovies = getHighRatedMovies();
        while (directors.size() != 5) {
            for (Movie movie : topHighRatedMovies) {
                directors.add(searchService.getDirector(movie.getTitle()));
                if (directors.size() == 5) {
                    break;
                }
            }
        }

        return new ArrayList<>(directors);
    }

    private List<Movie> getHighRatedMovies() {
        String formattedUrl = MessageFormat.format(highRatedUrl, apiKey);
        MovieApiWrapper movieApiWrapper = restTemplate.getForObject(formattedUrl, MovieApiWrapper.class);
        return new ArrayList<>(movieApiWrapper.getMovies());
    }
}
