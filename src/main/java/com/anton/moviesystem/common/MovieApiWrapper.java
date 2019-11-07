package com.anton.moviesystem.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieApiWrapper {

    @JsonProperty("results")
    List<Movie> movies;
    @JsonProperty("total_results")
    private String totalResults;


}
