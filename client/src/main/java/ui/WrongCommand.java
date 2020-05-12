package ui;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;

import java.util.Scanner;

public class WrongCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        System.out.println("Wrong command");
    }
}
