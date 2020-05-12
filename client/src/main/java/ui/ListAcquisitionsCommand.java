package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;

import java.util.Scanner;

public class ListAcquisitionsCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        serviceRent.getAllAcquisitions().forEach(System.out::println);
    }
}
