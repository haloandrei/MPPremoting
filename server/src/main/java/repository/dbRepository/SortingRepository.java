package repository.dbRepository;

import Utils.Sort;
import domain.BaseEntity;
import domain.validators.Validator;
import repository.Repository;

import java.io.Serializable;

public interface SortingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends Repository<ID, T> {

    Iterable<T> findAll(Sort sort);

    public void setValidator(Validator<T> validator);
}
