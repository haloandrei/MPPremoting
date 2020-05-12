package service;

public class Handlers {
    /*
    private Map<String, UnaryOperator<Message>> methodHandlers;
    private ServiceMovieInterface serviceMovie;
    private ServiceClientInterface serviceClient;
    private ServiceAcquisitionInterface serviceRent;

    public Handlers(ServiceMovieInterface serviceMovie, ServiceClientInterface serviceClient, ServiceAcquisitionInterface serviceRent) {
        this.methodHandlers = new HashMap<>();
        this.serviceMovie = serviceMovie;
        this.serviceClient = serviceClient;
        this.serviceRent = serviceRent;
        addHandlers();
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    public Map<String, UnaryOperator<Message>> getMethodHandlers() {
        return methodHandlers;
    }

    public void setMethodHandlers(Map<String, UnaryOperator<Message>> methodHandlers) {
        this.methodHandlers = methodHandlers;
    }

    public void addHandlers() {
        addHandler("addMovie", this::addMovie);
        addHandler("addClient", this::addClient);
        addHandler("addRent", this::addRent);
        addHandler("deleteClient", this::deleteClient);
        addHandler("deleteMovie", this::deleteMovie);
        addHandler("updateMovie", this::updateMovie);
        addHandler("updateClient", this::updateClient);
        addHandler("filterMoviesIfRented", this::filterMoviesIfRented);
        addHandler("findMostRentedMovie", this::findMostRentedMovie);
        addHandler("getAllClients", this::listClients);
        addHandler("getAllMovies", this::listMovies);
        addHandler("getAllRents", this::listRents);
        addHandler("getSortedMovies", this::sortMovies);

    }

    public Message<Movie> addMovie(Message<Movie> message) {
        Message<Movie> response = new Message<>("addMovie", null);
        try {
            serviceMovie.addMovie(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Client> addClient(Message<Client> message) {
        Message<Client> response = new Message<>("addClient", null);
        try {
            serviceClient.addClient(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Acquisition> addRent(Message<Acquisition> message) {
        Message<Acquisition> response = new Message<>("addRent", null);
        try {
            serviceRent.addRent(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Movie> updateMovie(Message<Movie> message) {
        Message<Movie> response = new Message<>("updateMovie", null);
        try {
            serviceMovie.updateMovie(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Client> updateClient(Message<Client> message) {
        Message<Client> response = new Message<>("updateClient", null);
        try {
            serviceClient.updateClient(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Integer> deleteMovie(Message<Integer> message) {
        Message<Integer> response = new Message<>("deleteMovie", null);
        try {
            serviceMovie.deleteMovie(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Integer> deleteClient(Message<Integer> message) {
        Message<Integer> response = new Message<>("deleteClient", null);
        try {
            serviceClient.deleteClient(message.getBody());
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<Set<String>> filterMoviesIfRented(Message<Set<String>> message) {
        Message<Set<String>> response = new Message<>("filterMoviesIfRented", null);
        try {
            HashSet<String> movies = serviceRent.filterMoviesIfRented().get();
            response.setBody(movies);
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<String> findMostRentedMovie(Message<String> message) {
        Message<String> response = new Message<>("findMostRentedMovie", null);
        try {
            String movie = serviceRent.findMostRentedMovie().get();
            response.setBody(movie);
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<ArrayList<Client>> listClients(Message<ArrayList<Client>> message) {
        Message<ArrayList<Client>> response = new Message<>("getAllClients", null);
        try {
            ArrayList<Client> clients = serviceClient.getAllClients().get();
            response.setBody(clients);
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<ArrayList<Movie>> listMovies(Message<ArrayList<Movie>> message) {
        Message<ArrayList<Movie>> response = new Message<>("getAllMovies", null);
        try {
            ArrayList<Movie> movies = serviceMovie.getAllMovies().get();
            response.setBody(movies);
        } catch (Exception e) {
            response.setHeader("error");

            response.setError(e.toString());
        }
        return response;
    }

    public Message<ArrayList<Acquisition>> listRents(Message<ArrayList<Acquisition>> message) {
        Message<ArrayList<Acquisition>> response = new Message<>("getAllRents", null);
        try {
            ArrayList<Acquisition> rents = serviceRent.getAllRents().get();
            response.setBody(rents);
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

    public Message<ArrayList<Movie>> sortMovies(Message<ArrayList<Movie>> message) {
        Message<ArrayList<Movie>> response = new Message<>("sortMovies", null);
        try {
            ArrayList<Movie> movies = serviceMovie.getSortedMovies().get();
            response.setBody(movies);
        } catch (Exception e) {
            response.setHeader("error");
            response.setError(e.toString());
        }
        return response;
    }

     */

}
