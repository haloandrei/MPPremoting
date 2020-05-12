package repository.dbRepository;

import Utils.Sort;
import domain.Acquisition;
import domain.validators.SQLRepoException;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class AcquisitionDBRepository implements SortingRepository<Integer, Acquisition> {

    private Validator<Acquisition> validator;
    @Autowired
    private JdbcOperations jdbcOperations;

    public void setValidator(Validator<Acquisition> validator){
        this.validator=validator;
    }

    @Override
    public Optional<Acquisition> findOne(Integer o) {
        String sql = "select * from acquisition where id=?;";
        List<Acquisition> c = jdbcOperations.query(sql, (rs, rowNum) ->
                new Acquisition(rs.getInt("id"), rs.getInt("movie_id"), rs.getInt("client_id")), o);
        if (c.size() == 1) {
            return Optional.of(c.get(0));
        }
        return Optional.empty();
    }

    //finds all acquisitions
    @Override
    public Iterable<Acquisition> findAll() {
        String sql = "select * from acquisition;";
        return jdbcOperations.query(sql, (resultSet, rowNum) -> {
            Acquisition acquisition =new Acquisition();
            acquisition.setId(resultSet.getInt("id"));
            acquisition.setClientId(resultSet.getInt("client_id"));
            acquisition.setMovieId(resultSet.getInt("movie_id"));
            return acquisition;
        });
    }

    @Override
    public Optional<Acquisition> save(Acquisition entity) throws ValidatorException {
        Optional<Acquisition> rent=this.findOne(entity.getId());
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sql = "insert into acquisition (id, movie_id, client_id) values (?,?,?)";
        jdbcOperations.update(sql,entity.getId(), entity.getMovieId(), entity.getClientId());
        return rent;
    }

    @Override
    public Optional<Acquisition> delete(Integer o) {
        Optional<Acquisition> rent=this.findOne(o);
        String sql = "delete from acquisition where id=?;";
        jdbcOperations.update(sql, o);
        return rent;
    }

    @Override
    public Optional<Acquisition> update(Acquisition entity) throws ValidatorException {
        AtomicReference<Optional<Acquisition>> rent = new AtomicReference<>(Optional.empty());
        this.findOne(entity.getId()).ifPresentOrElse((v)->{},()->{rent.set(Optional.of(entity));});
        validator.validate(entity);
        String sql = "update acquisition set movie_id=?, client_id=? where id=?;";
        jdbcOperations.update(sql, entity.getMovieId(), entity.getClientId(), entity.getId());
        return rent.get();
    }

    @Override
    public Iterable<Acquisition> findAll(Sort sort) {
        ArrayList<Acquisition> result = new ArrayList<>((Collection<? extends Acquisition>) findAll());
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
}
