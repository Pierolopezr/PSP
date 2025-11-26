package tarea_26;
/**
 * Partiendo de los ejemplos de "Hola Mundo" de los apuntes:
 * Modifica el puerto de conexión al 6666.
 * Programa una "conversación" donde el cliente envíe 3 mensajes "hardcodeados" (ej. "Mensaje 1...") y el servidor lea esos 3 mensajes.
 * Tras recibir los 3 mensajes, el servidor deberá responder con otros 3 mensajes "hardcodeados" (ej. "Respuesta 1...")
 * @author Piero
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static void main() {
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Esperando conexiones...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");

            // "Envolvemos" el ImputStream
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //leemos la respuesta (línea bloqueante)
            String mensajeRecibido1 = lector.readLine();
            String mensajeRecibido2 = lector.readLine();
            String mensajeRecibido3 = lector.readLine();
            System.out.println("Desde el cliente, nos envían:  " + mensajeRecibido1 + mensajeRecibido2 + mensajeRecibido3);

            PrintWriter serviCritor = new PrintWriter(socket.getOutputStream(), true);

            serviCritor.println(" Respuesta 1 ");
            serviCritor.println(" Respuesta 2 ");
            serviCritor.println(" Respuesta 3 ");

            socket.close();
            servidor.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
