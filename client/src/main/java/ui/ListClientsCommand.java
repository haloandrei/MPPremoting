package ui;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;

import java.util.Scanner;

public class ListClientsCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        serviceClient.getAllClients().forEach(System.out::println);
    }
}
