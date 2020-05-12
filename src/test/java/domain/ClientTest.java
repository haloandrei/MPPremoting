package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private static final Integer id1 = 1;
    private static final Integer id2 = 2;
    private static final String name1 = "name1";
    private static final String name2 = "name2";
    private static final int age1 = 20;
    private static final int age2 = 25;

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client(id1, name1, age1);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Names should be equal", name1, client.getName());
    }

    @Test
    public void testSetName() throws Exception {
        client.setName(name2);
        assertEquals("Names should be equal", name2, client.getName());
    }

    @Test
    public void testGetAge() throws Exception {
        assertEquals("age should be equal", age1, client.getAge());
    }

    @Test
    public void testSetAge() throws Exception {
        client.setAge(age2);
        assertEquals("age should be equal", age2, client.getAge());
    }
}
