package common;

import domain.Movie;
import domain.validators.MovieProjectException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public interface ServiceMovieInterface {

    ArrayList<Movie> getSortedMovies();

    void addMovie(Movie movie) throws MovieProjectException;

    void deleteMovie(Integer id) throws MovieProjectException;

    void updateMovie(Movie movie) throws MovieProjectException;

    ArrayList<Movie> getAllMovies();

}
