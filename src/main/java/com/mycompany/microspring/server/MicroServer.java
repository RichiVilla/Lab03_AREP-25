package com.mycompany.microspring.server;

import java.lang.reflect.Method;
import static java.nio.file.Paths.get;
import java.util.HashMap;
import java.util.Map;

public class MicroServer {

    public static Map<String, Method> services = new HashMap();

    public static void main(String[] args) throws Exception {
        loadComponents(args);
        System.out.println(simulateRequests("/greeting"));
        System.out.println(simulateRequests("/pi"));
        System.out.println(simulateRequests("/e"));
    }

    private static void loadComponents(String[] args) throws Exception {
        
        Class c = Class.forName(args[0]);
        
        
        if (!c.isAnnotationPresent(RestController.class)) {
            System.exit(0);
        }

        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(GetMapping.class)) {
                GetMapping a = m.getAnnotation(GetMapping.class);
                services.put(a.value(), m);
            }
        }

    }

    private static String simulateRequests(String route) throws Exception {
        Method m = services.get(route);
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + "{\"resp\":\"" + m.invoke(null, "pedro") + "\"}";
        return response;
    }

}
