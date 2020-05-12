package repository;

import domain.Movie;
import domain.validators.MovieValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class InMemoryRepositoryTest {
    private static final Integer id1 = 1;
    private static final Integer id2 = 2;
    private static final String title1 = "title1";
    private static final String title2 = "title2";
    private static final String genre1 = "genre1";
    private static final String genre2 = "genre2";
    private static final int year1 = 2000;
    private static final int year2 = 2005;
    private Movie movie1, movie2;
    private InMemoryRepository<Integer, Movie> repository;
    private Validator<Movie> validator;

    @Test
    public void testFindOne() throws Exception {
        validator = new MovieValidator();
        repository = new InMemoryRepository<>(validator);
        movie1 = new Movie(id1, title1, genre1, year1);
        movie2 = new Movie(id2, title2, genre2, year2);
        repository.save(movie1);
        repository.save(movie2);
        assertSame("movies should be the same", repository.findOne(1).get().getTitle(), movie1.getTitle());
    }

    @Test
    public void testDelete() throws Exception {
        validator = new MovieValidator();
        repository = new InMemoryRepository<>(validator);
        movie1 = new Movie(id1, title1, genre1, year1);
        movie2 = new Movie(id2, title2, genre2, year2);
        repository.save(movie1);
        repository.save(movie2);
        assertSame("movies should be the same", repository.delete(1).get().getTitle(), movie1.getTitle());
    }

    @Test
    public void testUpdate() throws Exception {
        validator = new MovieValidator();
        repository = new InMemoryRepository<>(validator);
        movie1 = new Movie(id1, title1, genre1, year1);
        movie2 = new Movie(id1, title1, genre2, year2);
        repository.save(movie1);
        assertSame("movies should be the same", repository.update(movie2).get().getTitle(), movie1.getTitle());
    }

    @Ignore
    @Test
    public void testFindAll() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test
    public void testSave() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        fail("Not yet tested");
    }
}