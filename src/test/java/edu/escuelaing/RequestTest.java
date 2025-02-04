package edu.escuelaing;

import static org.junit.Assert.*;
import org.junit.Test;

public class RequestTest {

    @Test
    public void testParseQueryString() {
        Request req = new Request("name=Erick&age=22");
        assertEquals("Erick", req.getValues("name"));
        assertEquals("22", req.getValues("age"));
    }

    @Test
    public void testEmptyQueryString() {
        Request req = new Request("");
        assertNull(req.getValues("name"));
    }

    @Test
    public void testNullQueryString() {
        Request req = new Request(null);
        assertNull(req.getValues("name"));
    }
}
