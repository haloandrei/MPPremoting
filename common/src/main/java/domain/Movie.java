package domain;

import java.io.Serializable;

public class Movie extends BaseEntity<Integer> implements Serializable {
    private String title;
    private String genre;
    private int year;

    public Movie() {
    }

    public Movie(Integer Id, String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.setId(Id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
