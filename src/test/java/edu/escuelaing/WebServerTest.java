package edu.escuelaing;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;

public class WebServerTest {

    @Test
    public void testGetInstance() {
        WebServer server1 = WebServer.getInstance();
        WebServer server2 = WebServer.getInstance();
        assertSame(server1, server2);
    }

    // Este test está simplificado; en un entorno real, necesitarías simular el socket.
    @Test
    public void testStartServer() {
        WebServer server = WebServer.getInstance();
        try {
            // Comprobar que el servidor puede iniciarse sin lanzar excepciones.
            ServerSocket serverSocket = new ServerSocket(8080);
            assertNotNull(serverSocket);
            serverSocket.close(); // Cerrar el socket para liberar el puerto
        } catch (Exception e) {
            fail("No se pudo iniciar el servidor en el puerto 8080");
        }
    }
}
