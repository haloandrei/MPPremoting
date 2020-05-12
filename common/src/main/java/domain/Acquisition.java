package domain;

import java.io.Serializable;

public class Acquisition extends BaseEntity<Integer> implements Serializable {
    private int movieId, clientId;

    public Acquisition(int id, int movieId, int clientId) {
        this.movieId = movieId;
        this.clientId = clientId;
        this.setId(id);
    }

    public Acquisition() {

    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Acquisition{" +
                "id=" + this.getId() +
                ", movie_Id=" + movieId +
                ", client_Id=" + clientId +
                '}';
    }
}
