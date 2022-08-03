package com.project.catalogonetfix.entitie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class Movie implements Serializable {

    @Id
    private String id;

    private String movieName;

    private String movieType;

    private String principalActor;

    private Integer createdAt;

    public Movie() {
    }

    public Movie(String id, String movieName, String movieType, String principalActor, Integer createdAt) {
        this.id = id;
        this.movieName = movieName;
        this.movieType = movieType;
        this.principalActor = principalActor;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getPrincipalActor() {
        return principalActor;
    }

    public void setPrincipalActor(String principalActor) {
        this.principalActor = principalActor;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(movieName, movie.movieName) && Objects.equals(movieType, movie.movieType) && Objects.equals(principalActor, movie.principalActor) && Objects.equals(createdAt, movie.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName, movieType, principalActor, createdAt);
    }
}
