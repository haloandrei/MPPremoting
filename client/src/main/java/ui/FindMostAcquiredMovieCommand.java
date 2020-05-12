package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;

import java.util.Scanner;

public class FindMostAcquiredMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("The most rented movie is " + serviceRent.findMostRentedMovie());
    }
}
