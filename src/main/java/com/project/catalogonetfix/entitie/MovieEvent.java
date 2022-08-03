package com.project.catalogonetfix.entitie;


import java.io.Serializable;
import java.util.Objects;

public class MovieEvent implements Serializable {

    private Long movieId;

    private String movieEvent;

    public MovieEvent() {
    }

    public MovieEvent(Long movieId, String movieEvent) {
        this.movieId = movieId;
        this.movieEvent = movieEvent;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieEvent() {
        return movieEvent;
    }

    public void setMovieEvent(String movieEvent) {
        this.movieEvent = movieEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEvent that = (MovieEvent) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(movieEvent, that.movieEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieEvent);
    }
}
