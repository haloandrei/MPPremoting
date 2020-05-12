package common;

import domain.Client;
import domain.validators.MovieProjectException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public interface ServiceClientInterface {

    ArrayList<Client> getSortedClients();

    void addClient(Client client) throws MovieProjectException;

    void deleteClient(Integer id) throws MovieProjectException;

    void updateClient(Client client) throws MovieProjectException;

    ArrayList<Client> getAllClients();
}
