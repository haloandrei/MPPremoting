package service;

import common.ServiceClientInterface;
import domain.Acquisition;
import domain.Client;
import domain.Movie;
import domain.validators.MovieProjectException;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.dbRepository.SortingRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceClient implements ServiceClientInterface {

    @Autowired
    private SortingRepository<Integer, Movie> repositoryMovie;
    @Autowired
    private SortingRepository<Integer, Client> repositoryClient;
    @Autowired
    private SortingRepository<Integer, Acquisition> repositoryRent;

    public ServiceClient(SortingRepository<Integer, Movie> repositoryMovie, SortingRepository<Integer, Client> repositoryClient, SortingRepository<Integer, Acquisition> repositoryRent) {
        this.repositoryMovie = repositoryMovie;
        this.repositoryClient = repositoryClient;
        this.repositoryRent = repositoryRent;
    }


    @Override
    public ArrayList<Client> getSortedClients() {
        return null;
    }

    /**
     * Adds a client in the service.
     *
     * @param client Client
     * @throws MovieProjectException if client is <code>null</code> | if client is not valid | if couldn't save to text file | if couldn't save to xml file
     */
    public void addClient(Client client) throws MovieProjectException {
        try {
            Client result = repositoryClient.save(client).orElse(client);
            Optional<Boolean> clientValidator = Optional.of(result.equals(client));
            clientValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }

    }


    /**
     * Removes a client from the service given its id.
     *
     * @param id Integer
     * @throws MovieProjectException if id is <code>null</code>
     */
    public void deleteClient(Integer id) throws MovieProjectException {
        try {
            this.repositoryRent.findAll().forEach(rent -> {
                if (rent.getClientId() == id) this.repositoryRent.delete(rent.getId());
                this.repositoryClient.delete(id).orElseThrow(() -> new ValidatorException("id not valid"));

            });
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }

    /**
     * Updates a client in the service.
     *
     * @param client Client
     * @throws MovieProjectException if client is <code>null</code> | if client is not valid. | if couldn't update the text file | if couldn't update the xml file
     */
    public void updateClient(Client client) throws MovieProjectException {
        AtomicReference<Optional<Client>> p = new AtomicReference<>(Optional.empty());
        try {
            repositoryClient.update(client).ifPresentOrElse((v) -> {
            }, () -> {
                p.set(Optional.of(client));
            });
            p.get().orElseThrow(() -> new MovieProjectException("id not existing"));
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }

    /**
     * Returns the client with given id.
     *
     * @param id Integer
     * @return Client
     * @throws MovieProjectException if id is <code>null</code>
     */
    public Optional<Client> getClientById(Integer id) throws MovieProjectException {
        try {
            return this.repositoryClient.findOne(id);
        } catch (Exception e) {
            throw new MovieProjectException(e);
        }
    }

    /**
     * Returns the set of clients.
     *
     * @return Set of clients
     */
    public ArrayList<Client> getAllClients() {
        Iterable<Client> clients = repositoryClient.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));
    }
}
