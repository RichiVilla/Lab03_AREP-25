
package edu.escuelaing.spring;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Base64;
import java.util.Locale;


@RestController
public class ServicesAvaliable{
    private static final String STATIC_FILES_DIR = "src/main/resources";


    @GetMapping("/hello")
    public String hello(@RequestParameters(value = "name", defaultValue = "Usuario") String name) {
        return "Hola " + name;
    }

    @GetMapping("/sum")
    public static String sum(@RequestParameters(value = "a") String a, @RequestParameters(value = "b") String b) {
        try {
            int num1 = Integer.parseInt(a);
            int num2 = Integer.parseInt(b);
            int result = num1 + num2;
            return "La suma de " + num1 + " y " + num2 + " es " + result;
        } catch (NumberFormatException e) {
            return "Error: Los parámetros deben ser números enteros.";
        }
    }

    @GetMapping("/manana")
    public String manana() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String dayName = tomorrow.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        return "Mañana será " + dayName + ".  ";
    }

    @GetMapping("/fecha-hora")
    public String fechaHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Fecha y hora actual: " + LocalDateTime.now().format(formatter);
    }

    @GetMapping("/ip")
    public String miIp() {
        try {
            return "La IP del servidor es: " + InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "No se pudo obtener la IP";
        }
    }


    @GetMapping("/sistema")
    public String sistema() {
        String os = System.getProperty("os.name");
        String version = System.getProperty("os.version");
        return "Sistema operativo: " + os + " (versión " + version + ") ";
    }

    @GetMapping("/euler")
    public static String euler(){
        return "El valor de Euler es: " + String.valueOf(Math.E);
    }

    @GetMapping("/memoria")
    public String memoria() {
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        return String.format("Memoria usada: %d MB / %d MB", (totalMemory - freeMemory), totalMemory);
    }


    @GetMapping("/autor")
    public String autor() {
        return "Este código fue desarrollado por Ricardo Villamizar. ";
    }

    @GetMapping("/staticfile")
    public static String serveStatic(@RequestParameters(value = "file", defaultValue = "index.html") String fileName) {
        return serveStaticFile(fileName);
    }

    @GetMapping("/pi")
    public String getPi() {
        return "El valor de Pi es: " + String.valueOf(Math.PI);
    }



        @GetMapping("/")
        public static String serveIndex() {
            return serveStaticFile("index.html");
        }




        public static String serveStaticFile(String fileName) {
            File file = new File(STATIC_FILES_DIR + File.separator + fileName);

            if (file.exists() && !file.isDirectory()) {
                try {
                    String contentType = determineContentType(fileName);
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    if (contentType.startsWith("image")) {
                        String base64Image = Base64.getEncoder().encodeToString(fileContent);
                        String htmlResponse = "<!DOCTYPE html>\r\n"
                                + "<html>\r\n"
                                + "    <head>\r\n"
                                + "        <title>Image</title>\r\n"
                                + "    </head>\r\n"
                                + "    <body>\r\n"
                                + "        <center><img src=\"data:" + contentType + ";base64," + base64Image + "\" alt=\"image\"></center>\r\n"
                                + "    </body>\r\n"
                                + "</html>";
                        return "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: " + htmlResponse.length() + "\r\n\r\n" + htmlResponse;
                    } else {
                        return "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-Length: " + fileContent.length + "\r\n\r\n" + new String(fileContent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "HTTP/1.1 500 Internal Server Error\r\n\r\nError reading file";
                }
            } else {
                return "HTTP/1.1 404 Not Found\r\n\r\nFile not found";
            }
        }

        private static String determineContentType(String fileName) {
            if (fileName.endsWith(".html")) {
                return "text/html";
            } else if (fileName.endsWith(".js")) {
                return "application/javascript";
            } else if (fileName.endsWith(".css")) {
                return "text/css";
            } else if (fileName.endsWith(".png")) {
                return "image/png";
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return "image/jpeg";
            }
            return "application/octet-stream";
        }
    }
