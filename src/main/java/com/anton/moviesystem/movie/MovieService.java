package com.anton.moviesystem.movie;

import com.anton.moviesystem.common.MovieApiWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final RestTemplate restTemplate;

    @Value("${moviedb.apikey}")
    private String apiKey;

    @Value("${moviedb.movie.discovery}")
    private String discoverMovieUrl;

    String getLastThreeYearsReleases(String countryCode) {
        StringBuilder result = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = 0; i < 3; i++) {
            result.append("[ ").append(currentYear - i).append(" ").append(countryCode).append(" - ")
                    .append(getMovieReleases(countryCode, String.valueOf(currentYear - i))).append(" MOVIES ] ");
        }

        return result.toString();

    }

    private String getMovieReleases(String countryCode, String year) {
        String formattedUrl = MessageFormat.format(discoverMovieUrl, apiKey, countryCode, year);
        MovieApiWrapper movieApiWrapper = restTemplate.getForObject(formattedUrl, MovieApiWrapper.class);
        log.info(movieApiWrapper + " [RECEIVED]");
        return movieApiWrapper.getTotalResults();
    }
}
