package repository.dbRepository;

import Utils.Sort;
import domain.Client;
import domain.validators.SQLRepoException;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClientDBRepository implements SortingRepository<Integer, Client> {
    private Validator<Client> validator;

    @Autowired
    private JdbcOperations jdbcOperations;

    public void setValidator(Validator<Client> validator) {
        this.validator = validator;
    }
    //finds all clients
    @Override
    public Iterable<Client> findAll(Sort sort) {
        ArrayList<Client> result = new ArrayList<>((Collection<? extends Client>) findAll());
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
                    Stream.of(s.getCurrentDirection()).filter(c -> !c).anyMatch((v) -> {
                        comp.set(comp.get() * -1);
                        return v;
                    });
                    return comp.get();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new SQLRepoException(e.getMessage(), e);
                }
            }));
        });
        return result;

    }

    @Override
    public Optional<Client> findOne(Integer o) {
        String sql = "select * from client where id=?;";
        List<Client> c = jdbcOperations.query(sql, (rs, rowNum) ->
                new Client(rs.getInt("id"), rs.getString("name"), rs.getInt("age")), o);
        AtomicReference<Optional<Client>> d = new AtomicReference<>(Optional.empty());
        c.forEach((v)->{d.set(Optional.of(v));});
        return d.get();
    }

    @Override
    public Iterable<Client> findAll() {
        String sql = "select * from client;";
        return jdbcOperations.query(sql, (resultSet, rowNum) -> {
            Client client = new Client();
            client.setId(resultSet.getInt("id"));
            client.setName(resultSet.getString("name"));
            client.setAge(resultSet.getInt("age"));
            return client;
        });

    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> client = this.findOne(entity.getId());
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sql = "insert into client values (?,?,?);";
        jdbcOperations.update(sql, entity.getId(), entity.getName(), String.valueOf(entity.getAge()));
        return client;
    }

    @Override
    public Optional<Client> delete(Integer o) {
        Optional<Client> client = this.findOne(o);
        String sql = "delete from client where id=?";
        jdbcOperations.update(sql, o);
        return client;
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        AtomicReference<Optional<Client>> client = new AtomicReference<>(Optional.empty());
        this.findOne(entity.getId()).ifPresentOrElse((v)->{},()->{client.set(Optional.of(entity));});
        validator.validate(entity);
        String sql = "update client set name=?,  age=? where id=?;";
        jdbcOperations.update(sql, entity.getName(), entity.getAge(), entity.getId());
        return client.get();
    }
}
