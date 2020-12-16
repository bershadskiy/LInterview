package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void setAge() {
        Exception exception = null;
        try {
            new Person(-2);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull("Exception should be thrown for negative age", exception);
    }
}