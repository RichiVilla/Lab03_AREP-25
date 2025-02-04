package edu.escuelaing;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebServer {
    private static WebServer instance;
    private static final int PORT = 8080;

    private WebServer() {}

    public static WebServer getInstance() {
        if (instance == null) {
            instance = new WebServer();
        }
        return instance;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Listening on port " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
        }
    }

    private void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();
        String inputLine = in.readLine();
        if (inputLine != null) {
            String[] requestParts = inputLine.split(" ");
            String path = requestParts[1];

            if (path.startsWith("/App/")) {
                handleServiceRequest(path, out);
            } else {
                handleStaticFileRequest(path, out);
            }
        }
    }

    private void handleServiceRequest(String path, OutputStream out) throws IOException {
        String[] pathParts = path.split("\\?");
        String servicePath = pathParts[0];
        String queryString = pathParts.length > 1 ? pathParts[1] : "";

        Service service = App.getServices().get(servicePath);
        if (service != null) {
            Request req = new Request(queryString);
            Response resp = new Response();
            String result = service.getValue(req, resp);
            sendResponse(out, "200 OK", "text/plain", result);
        } else {
            sendResponse(out, "404 Not Found", "text/plain", "Service not found");
        }
    }

    private void handleStaticFileRequest(String path, OutputStream out) throws IOException {
        // Si la ruta es "/", redirigir a "/index.html"
        if ("/".equals(path)) {
            path = "/index.html";
        }

        String filePath = App.getStaticFilesLocation() + path;
        File file = new File(filePath);
        System.out.println("Trying to serve file: " + file.getAbsolutePath()); // Log for debugging
        if (file.exists() && !file.isDirectory()) {
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            byte[] fileContent = Files.readAllBytes(file.toPath());
            sendResponse(out, "200 OK", contentType, new String(fileContent));
        } else {
            System.out.println("File not found: " + file.getAbsolutePath()); // Log for debugging
            sendResponse(out, "404 Not Found", "text/plain", "File not found");
        }
    }

    private void sendResponse(OutputStream out, String status, String contentType, String body) throws IOException {
        PrintWriter writer = new PrintWriter(out, true);
        writer.println("HTTP/1.1 " + status);
        writer.println("Content-Type: " + contentType);
        writer.println();
        writer.println(body);
    }
}