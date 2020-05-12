package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class DeleteMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        try {
            serviceMovie.deleteMovie(id);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
