package edu.escuelaing.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Spring {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, MalformedURLException {
        if (args.length < 1) {
            System.out.println("Uso: java -cp target/WebServer-1.0-SNAPSHOT.jar edu.escuelaing.spring.Spring <nombre_clase_servicio>");
            return;
        }

        // Cargar la clase desde el argumento de la línea de comandos
        Class<?> c = Class.forName(args[0]);
        Map<String, Method> services = new HashMap<>();

        if (c.isAnnotationPresent(RestController.class)) {
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(GetMapping.class)) {
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }

        // Leer la URL del servicio desde la línea de comandos
        if (args.length < 2) {
            System.out.println("Uso: java -cp target/WebServer-1.0-SNAPSHOT.jar edu.escuelaing.spring.Spring <nombre_clase_servicio> <url>");
            return;
        }

        URL serviceUrl = new URL(args[1]);
        String path = serviceUrl.getPath();
        String query = serviceUrl.getQuery();

        System.out.println("path: " + path);
        System.out.println("query: " + query);

        // Obtener solo el nombre del servicio sin prefijos
        String[] pathParts = path.split("/");
        String serviceName = pathParts[pathParts.length - 1];

        System.out.println("Service Name: " + serviceName);

        Method ms = services.get("/" + serviceName);

        if (ms != null) {
            Object[] argsToPass = extractArguments(ms, query);
            System.out.println("Rta al servicio: " + ms.invoke(null, argsToPass));
        } else {
            System.out.println("Servicio no encontrado: " + serviceName);
        }
    }

    private static Object[] extractArguments(Method method, String query) {
        Map<String, String> queryParams = parseQuery(query);
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(RequestParameters.class)) {
                RequestParameters annotation = parameters[i].getAnnotation(RequestParameters.class);
                String paramName = annotation.value();
                String value = queryParams.getOrDefault(paramName, annotation.defaultValue());
                args[i] = value;
            }
        }
        return args;
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length > 1) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return queryParams;
    }
}
