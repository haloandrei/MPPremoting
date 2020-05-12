package repository.dbRepository;

import Utils.Sort;
import domain.Client;
import domain.Movie;
import domain.validators.SQLRepoException;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class MovieDBRepository implements SortingRepository<Integer, Movie> {

    private Validator<Movie> validator;
    @Autowired
    private JdbcOperations jdbcOperations;

    public void setValidator(Validator<Movie> validator){
        this.validator=validator;
    }
    //finds all movies
    @Override
    public Iterable<Movie> findAll(Sort sort) {
        ArrayList<Movie> result = new ArrayList<>((Collection<? extends Movie>) findAll());
        List<Sort> sortList = sort.getSortList();
        sortList.forEach((s) -> {
            List<String> fieldsList = s.getFields();
            fieldsList.forEach(p -> result.sort((c1, c2) -> {
                try {
                    Field f1 = c1.getClass().getDeclaredField(p);
                    f1.setAccessible(true);
                    String n1 = (String) f1.get(c1);
                    Field f2 = c2.getClass().getDeclaredField(p);
                    f2.setAccessible(true);
                    String n2 = (String) f2.get(c2);
                    AtomicInteger comp = new AtomicInteger(n1.compareTo(n2));
                    Stream.of(s.getCurrentDirection()).filter(c->!c).anyMatch((v)->{ comp.set(comp.get() * -1); return v;});
                    return comp.get();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new SQLRepoException(e.getMessage(), e);
                }
            }));
        });
        return result;
    }

    @Override
    public Optional<Movie> findOne(Integer o) {
        String sql = "select * from movie where id=?;";
        List<Movie> c = jdbcOperations.query(sql, (rs, rowNum) ->
                new Movie(rs.getInt("id"), rs.getString("title"),rs.getString("genre"), rs.getInt("year")), o);
        if (c.size() == 1) {
            return Optional.of(c.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Movie> findAll() {
        String sql = "select * from movie;";
        return jdbcOperations.query(sql, (resultSet, rowNum) -> {
            Movie movie=new Movie();
            movie.setId(resultSet.getInt("id"));
            movie.setTitle(resultSet.getString("title"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setYear(resultSet.getInt("year"));
            return movie;
        });
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> movie=this.findOne(entity.getId());
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sql = "insert into movie (id, title,genre, year) values (?,?,?,?)";
        jdbcOperations.update(sql,entity.getId(), entity.getTitle(), entity.getGenre(), entity.getYear());
        return movie;
    }

    @Override
    public Optional<Movie> delete(Integer o) {
        Optional<Movie> movie=this.findOne(o);
        String sql = "delete from movie where id=?;";
        jdbcOperations.update(sql, o);
        return movie;
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        String sql = "update movie set title=?, genre=?, year=? where id=?;";
        AtomicReference<Optional<Movie>> movie = new AtomicReference<>(Optional.empty());
        this.findOne(entity.getId()).ifPresentOrElse((v)->{},()->{movie.set(Optional.of(entity));});
        validator.validate(entity);
        jdbcOperations.update(sql, entity.getTitle(), entity.getGenre(), entity.getYear(), entity.getId());
        return movie.get();
    }
}
