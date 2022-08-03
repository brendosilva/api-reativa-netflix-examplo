package com.project.catalogonetfix.controllers;

import com.project.catalogonetfix.entitie.Movie;
import com.project.catalogonetfix.entitie.MovieEvent;
import com.project.catalogonetfix.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/netflix")
public class MovieController {
    private MovieRepository movieRepository;

    public MovieController (MovieRepository repository) {
        this.movieRepository = repository;
    }

    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Movie>> getMovieById(@PathVariable final String id) {
        return movieRepository.findById(id)
                .map(movie -> ResponseEntity.ok(movie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> createMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PostMapping("{id}")
    public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") String id,
                                                   @RequestBody Movie movie){
        return movieRepository.findById(id)
                .flatMap(existingMovie -> {
                    existingMovie.setMovieName(movie.getMovieName());
                    existingMovie.setMovieType(movie.getMovieType());
                    existingMovie.setPrincipalActor(movie.getPrincipalActor());
                    existingMovie.setCreatedAt(movie.getCreatedAt());
                    return movieRepository.save(existingMovie);
                })
                .map(updateMovie -> ResponseEntity.ok(updateMovie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteMovieNetflix(@PathVariable(value = "id") String id) {
        return movieRepository.findById(id)
                .flatMap(existingMovie ->
                        movieRepository.delete(existingMovie)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllMovies() {
        return movieRepository.deleteAll();
    }

    @GetMapping(value = "/netflix-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getNetflixEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new MovieEvent(val, "Netlifx Event")
                );
    }


}
