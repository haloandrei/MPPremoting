package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author radu.
 */
public class MovieTest {
    private static final Integer id1 = 1;
    private static final Integer id2 = 2;
    private static final String title1 = "title1";
    private static final String title2 = "title2";
    private static final String genre1 = "genre1";
    private static final String genre2 = "genre2";
    private static final int year1 = 2000;
    private static final int year2 = 2005;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movie = new Movie(id1,title1,genre1,year1);
    }

    @After
    public void tearDown() throws Exception {
        movie = null;
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals("Serial numbers should be equal", title1, movie.getTitle());
    }

    @Test
    public void testSetTitle() throws Exception {
        movie.setTitle(title2);
        assertEquals("Serial numbers should be equal", title2, movie.getTitle());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", id1, movie.getId());
    }

    @Test
    public void testSetId() throws Exception {
        movie.setId(id2);
        assertEquals("Ids should be equal", id2, movie.getId());
    }

    @Test
    public void testGetGenre() throws Exception {
        assertEquals("genres should be equal",genre1, movie.getGenre());
    }

    @Test
    public void testSetGenre() throws Exception {
        movie.setGenre(genre2);
        assertEquals("genres should be equal",genre2,movie.getGenre());
    }

    @Test
    public void testGetYear() throws Exception {
        assertEquals("years should be equal",year1,movie.getYear());
    }

    @Test
    public void testSetYear() throws Exception {
        movie.setYear(year2);
        assertEquals("years should be equal",year2,movie.getYear());
    }
}