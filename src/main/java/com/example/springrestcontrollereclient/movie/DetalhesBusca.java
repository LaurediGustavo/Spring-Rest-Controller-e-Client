package com.example.springrestcontrollereclient.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DetalhesBusca {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private Integer year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Ratings")
    private Ratings ratings;
    @JsonProperty("Metascore")
    private String metascore;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("totalSeasons")
    private String totalSeasons;
    @JsonProperty("Response")
    private String response;
}


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Ratings {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}
