package config;

import common.ServiceAcquisitionInterface;
import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import domain.Acquisition;
import domain.Client;
import domain.Movie;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.RentValidator;
import domain.validators.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.dbRepository.ClientDBRepository;
import repository.dbRepository.MovieDBRepository;
import repository.dbRepository.AcquisitionDBRepository;
import repository.dbRepository.SortingRepository;
import service.ServiceAcquisition;
import service.ServiceClient;
import service.ServiceMovie;

@Configuration
public class ServerConfig {
    @Bean
    RmiServiceExporter rmiServiceClient() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ServiceClientInterface");
        rmiServiceExporter.setServiceInterface(ServiceClientInterface.class);
        rmiServiceExporter.setService(clientService());
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter rmiServiceMovie() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ServiceMovieInterface");
        rmiServiceExporter.setServiceInterface(ServiceMovieInterface.class);
        rmiServiceExporter.setService(movieService());
        return rmiServiceExporter;
    }
    @Bean
    RmiServiceExporter rmiServiceRent() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ServiceAcquisitionInterface");
        rmiServiceExporter.setServiceInterface(ServiceAcquisitionInterface.class);
        rmiServiceExporter.setService(rentService());
        return rmiServiceExporter;
    }

    @Bean
    ServiceClientInterface clientService() {
        return new ServiceClient(repoMovie(),repoClient(),repoRent());
    }

    @Bean
    ServiceMovieInterface movieService()  {
        return new ServiceMovie(repoMovie(),repoClient(),repoRent());
    }

    @Bean
    ServiceAcquisitionInterface rentService()  {
        return new ServiceAcquisition(repoMovie(),repoClient(),repoRent());
    }

    @Bean
    SortingRepository<Integer, Movie> repoMovie() {
        SortingRepository<Integer,Movie> repo=new MovieDBRepository();
        Validator<Movie> validator=new MovieValidator();
        repo.setValidator(validator);
        return repo;
    }

    @Bean
    SortingRepository<Integer, Acquisition> repoRent() {
        SortingRepository<Integer, Acquisition> repo=new AcquisitionDBRepository();
        Validator<Acquisition> validator=new RentValidator();
        repo.setValidator(validator);
        return repo;
    }

    @Bean
    SortingRepository<Integer, Client> repoClient() {
        SortingRepository<Integer,Client> repo=new ClientDBRepository();
        Validator<Client> validator=new ClientValidator();
        repo.setValidator(validator);
        return repo;
    }
}
