package ui;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;

import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner, ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent);
}
