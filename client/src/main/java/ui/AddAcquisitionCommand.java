package ui;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import domain.Acquisition;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class AddAcquisitionCommand implements Command {
    public AddAcquisitionCommand() {}

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Acquisition Id: ");
        int rentId = scanner.nextInt();
        System.out.println("Movie Id: ");
        int movieId = scanner.nextInt();
        System.out.println("Client Id: ");
        int clientId = scanner.nextInt();
        try{
            serviceRent.addAcquisition(new Acquisition(rentId, movieId,clientId));
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
