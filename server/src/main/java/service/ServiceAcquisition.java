package service;

import common.ServiceAcquisitionInterface;
import domain.Acquisition;
import domain.Client;
import domain.Movie;
import domain.validators.MovieProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.dbRepository.SortingRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceAcquisition implements ServiceAcquisitionInterface {
    @Autowired
    private SortingRepository<Integer, Movie> repositoryMovie;
    @Autowired
    private SortingRepository<Integer, Client> repositoryClient;
    @Autowired
    private SortingRepository<Integer, Acquisition> repositoryRent;

    public ServiceAcquisition(SortingRepository<Integer, Movie> repositoryMovie, SortingRepository<Integer, Client> repositoryClient, SortingRepository<Integer, Acquisition> repositoryRent) {
        this.repositoryMovie = repositoryMovie;
        this.repositoryClient = repositoryClient;
        this.repositoryRent = repositoryRent;
    }

    public void addAcquisition(Acquisition acquisition) throws MovieProjectException {
        Iterable<Movie> movies = repositoryMovie.findAll();
        Iterable<Client> clients = repositoryClient.findAll();
        List<Movie> filteredMovies = StreamSupport.stream(movies.spliterator(), false)
                .filter(movie -> movie.getId().equals(acquisition.getMovieId())).collect(Collectors.toList());
        List<Client> filteredClients = StreamSupport.stream(clients.spliterator(), false)
                .filter(client -> client.getId().equals(acquisition.getClientId())).collect(Collectors.toList());
        filteredMovies.stream().findAny().orElseThrow(() -> new MovieProjectException("There's no movie with that id!"));
        filteredClients.stream().findAny().orElseThrow(() -> new MovieProjectException("There's no client with that id!"));
        try {
            Acquisition result = repositoryRent.save(acquisition).orElse(acquisition);
            Optional<Boolean> rentValidator = Optional.of(result.equals(acquisition));
            rentValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }

    public ArrayList<Acquisition> getAllAcquisitions() {
        Iterable<Acquisition> rents = repositoryRent.findAll();
        return StreamSupport.stream(rents.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));
    }

    public HashSet<String> filterMoviesIfRented() {
        Iterable<Movie> movies = repositoryMovie.findAll();
        Iterable<Acquisition> rents = repositoryRent.findAll();
        Set<Integer> rentedMoviesIds = StreamSupport.stream(rents.spliterator(), false).map(Acquisition::getMovieId).collect(Collectors.toSet());
        return StreamSupport.stream(movies.spliterator(), false).filter(movie -> rentedMoviesIds.contains(movie.getId())).map(Movie::getTitle).collect(Collectors.toCollection(HashSet::new));
    }

    public String findMostRentedMovie() {
        Iterable<Movie> movies = repositoryMovie.findAll();
        Iterable<Acquisition> rents = repositoryRent.findAll();
        List<Integer> rentedMoviesIds = StreamSupport.stream(rents.spliterator(), false).map(Acquisition::getMovieId).collect(Collectors.toList());
        return repositoryMovie.findOne(rentedMoviesIds.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey()).get().getTitle();
    }
}
