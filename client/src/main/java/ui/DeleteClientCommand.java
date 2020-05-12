package ui;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class DeleteClientCommand implements Command {
    public DeleteClientCommand() {}

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        try{
            serviceClient.deleteClient(id);
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
