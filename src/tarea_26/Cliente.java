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
import java.net.Socket;

public class Cliente {
    static void main() {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

        try {
            Socket socket = new Socket();
            socket.connect(dir);

            System.out.println("Conectado al servidor");

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            // Enviamos el mensaje al servidor
            escritor.println(" mensaje 1 ");
            escritor.println(" mensaje 2 ");
            escritor.println(" mensaje 3 ");


            // "Envolvemos" el ImputStream
            BufferedReader clienLector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //leemos la respuesta (línea bloqueante)

            String mensajeRecibido1 = clienLector.readLine();
            String mensajeRecibido2 = clienLector.readLine();
            String mensajeRecibido3 = clienLector.readLine();
            System.out.println("Desde el servidor, nos envían:  " + mensajeRecibido1 + mensajeRecibido2 + mensajeRecibido3);

            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
