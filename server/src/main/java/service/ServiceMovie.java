package service;


import Utils.Sort;
import common.ServiceMovieInterface;
import domain.Client;
import domain.Movie;
import domain.Acquisition;
import domain.validators.MovieProjectException;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.dbRepository.SortingRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceMovie implements ServiceMovieInterface {
    @Autowired
    private SortingRepository<Integer, Movie> repositoryMovie;
    @Autowired
    private SortingRepository<Integer, Client> repositoryClient;
    @Autowired
    private SortingRepository<Integer, Acquisition> repositoryRent;

    public ServiceMovie(SortingRepository<Integer, Movie> repositoryMovie, SortingRepository<Integer, Client> repositoryClient, SortingRepository<Integer, Acquisition> repositoryRent) {
        this.repositoryMovie = repositoryMovie;
        this.repositoryClient = repositoryClient;
        this.repositoryRent = repositoryRent;
    }


    public ArrayList<Movie> getSortedMovies() {

        Sort sort = new Sort(Sort.direction.DESC, "genre", "title");

        return StreamSupport.stream(repositoryMovie.findAll(sort).spliterator(), false).collect(Collectors.toCollection(ArrayList::new));

    }


    public void addMovie(Movie movie) throws MovieProjectException {
        try {
            Movie result = repositoryMovie.save(movie).orElse(movie);
            Optional<Boolean> movieValidator = Optional.of(result.equals(movie));
            movieValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }

    public void deleteMovie(Integer id) throws MovieProjectException {
        try {
            this.repositoryRent.findAll().forEach(rent -> {
                if (rent.getMovieId() == id) this.repositoryRent.delete(rent.getId());
            });
            this.repositoryMovie.delete(id).orElseThrow(() -> new ValidatorException("id not valid"));

        } catch (Exception e) {
            throw new MovieProjectException(e.getMessage(), e);
        }
    }

    public void updateMovie(Movie movie) throws MovieProjectException {
        AtomicReference<Optional<Movie>> p = new AtomicReference<>(Optional.empty());
        try {
            repositoryMovie.update(movie).ifPresentOrElse((v) -> {
            }, () -> {
                p.set(Optional.of(movie));
            });
            p.get().orElseThrow(() -> new MovieProjectException("id not existing"));
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }


    public ArrayList<Movie> getAllMovies() {
        Iterable<Movie> movies = repositoryMovie.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));
    }
}
