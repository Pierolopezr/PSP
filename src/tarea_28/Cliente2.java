package tarea_28;
/**
 * @author PIERO LÓPEZ ROSAS
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    static void main() {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
        String ox;
        try {
            Socket cliente2 = new Socket();
            cliente2.connect(dir);

            System.out.println("Conectado al servidor");

            Scanner op = new Scanner(System.in);

            PrintWriter escritor = new PrintWriter(cliente2.getOutputStream(), true);
            BufferedReader clienLector = new BufferedReader(new InputStreamReader(cliente2.getInputStream()));

            while(true){
                System.out.println("Escribe el mensaje que quieres enviarle al servidor" );
                ox = op.nextLine();
                // Envía el mensaje al servidor
                escritor.println(ox);
                if(ox.equalsIgnoreCase("adios")) break;

                // recibe el mensaje y lo lee
                String mensajeRecibido1 = clienLector.readLine();
                System.out.println("Desde el servidor, envía a cliente 2:  " + mensajeRecibido1 );
            }

            cliente2.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}