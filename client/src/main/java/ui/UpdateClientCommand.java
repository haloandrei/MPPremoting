package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;
import domain.Client;
import domain.validators.MovieProjectException;

import java.util.Scanner;

public class UpdateClientCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        try {
            serviceClient.updateClient(new Client(id, name, age));
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
