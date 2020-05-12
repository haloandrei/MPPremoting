package common;

import domain.Acquisition;
import domain.validators.MovieProjectException;

import java.util.ArrayList;
import java.util.HashSet;

public interface ServiceAcquisitionInterface {

    void addAcquisition(Acquisition acquisition) throws MovieProjectException;

    ArrayList<Acquisition> getAllAcquisitions();

    HashSet<String> filterMoviesIfRented();

    String findMostRentedMovie();
}
