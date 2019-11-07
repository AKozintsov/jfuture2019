package com.anton.moviesystem.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Movie {

    @JsonProperty("original_title")
    private String title;
}
