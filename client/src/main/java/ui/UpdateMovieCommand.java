package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;
import domain.Movie;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class UpdateMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Title: ");
        String title = scanner.next();
        System.out.println("Genre: ");
        String genre = scanner.next();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        try {
            serviceMovie.updateMovie(new Movie(id, title, genre, year));
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
