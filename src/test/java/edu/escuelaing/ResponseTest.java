package edu.escuelaing;

import static org.junit.Assert.*;
import org.junit.Test;

public class ResponseTest {

    @Test
    public void testSetAndGetBody() {
        Response resp = new Response();
        resp.setBody("Test Body");
        assertEquals("Test Body", resp.getBody());
    }
}

