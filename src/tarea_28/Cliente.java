package tarea_28;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Piero
 * servidor inicia el servidor y luego inicia los hilos
 *
 * cliente conecta
 * cliente2 conecta
 * gestor de hilos en funcionamiento
 */

public class Cliente {
    static void main() {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
        String ox;
        try {
            Socket cliente = new Socket();
            cliente.connect(dir);

            System.out.println("Conectado al servidor");

            Scanner op = new Scanner(System.in);

            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader clienLector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            while(true){
                System.out.println("Escribe el mensaje que quieres enviarle al servidor" );
                ox = op.nextLine();
                // Envía el mensaje al servidor
                escritor.println(ox);
                if(ox.equalsIgnoreCase("adios")) break;

                // recibe el mensaje y lo lee
                String mensajeRecibido1 = clienLector.readLine();
                System.out.println("Desde el servidor, envía a cliente 1:  " + mensajeRecibido1 );
            }

            cliente.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}