package edu.escuelaing;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

public class AppTest {

    @Test
    public void testStaticFilesLocation() {
        App.staticfiles("src/main/resources");
        assertEquals("src/main/resources", App.getStaticFilesLocation());
    }

    @Test
    public void testServiceRegistration() {
        App.get("/test", (req, resp) -> "Test Service");
        Map<String, Service> services = App.getServices();
        assertTrue(services.containsKey("/App/test"));
        assertEquals("Test Service", services.get("/App/test").getValue(new Request(null), new Response()));
    }

    @Test
    public void testHelloService() {
        App.get("/hello", (req, resp) -> {
            String name = req.getValues("name");
            return name != null && !name.isEmpty() ? "Hello " + name : "Hello World!";
        });

        Request requestWithName = new Request("name=Erick");
        Request requestWithoutName = new Request(null);

        assertEquals("Hello Erick", App.getServices().get("/App/hello").getValue(requestWithName, new Response()));
        assertEquals("Hello World!", App.getServices().get("/App/hello").getValue(requestWithoutName, new Response()));
    }

    @Test
    public void testPiService() {
        App.get("/pi", (req, resp) -> String.valueOf(Math.PI));
        assertEquals(String.valueOf(Math.PI), App.getServices().get("/App/pi").getValue(new Request(null), new Response()));
    }

    @Test
    public void testSumService() {
        App.get("/sum", (req, resp) -> {
            String param1 = req.getValues("a");
            String param2 = req.getValues("b");
            if (param1 != null && param2 != null) {
                try {
                    int a = Integer.parseInt(param1);
                    int b = Integer.parseInt(param2);
                    return String.valueOf(a + b);
                } catch (NumberFormatException e) {
                    return "Invalid input";
                }
            }
            return "Missing parameters";
        });

        Request validRequest = new Request("a=3&b=5");
        Request missingParametersRequest = new Request(null);
        Request invalidInputRequest = new Request("a=3&b=abc");

        assertEquals("8", App.getServices().get("/App/sum").getValue(validRequest, new Response()));
        assertEquals("Missing parameters", App.getServices().get("/App/sum").getValue(missingParametersRequest, new Response()));
        assertEquals("Invalid input", App.getServices().get("/App/sum").getValue(invalidInputRequest, new Response()));
    }

}
