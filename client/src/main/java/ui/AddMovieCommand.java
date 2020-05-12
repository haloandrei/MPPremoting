package ui;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import domain.Movie;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class AddMovieCommand implements Command {
    public AddMovieCommand() {
    }

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
            serviceMovie.addMovie(new Movie(id, title, genre, year));
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
