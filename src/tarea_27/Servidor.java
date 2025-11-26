package tarea_27;

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
            PrintWriter serviCritor = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                //Recibe el mensaje y lo lee
                String mensajeRecibido1 = lector.readLine();
                if(mensajeRecibido1.equalsIgnoreCase("adios")|| mensajeRecibido1 == null)break;

                // imprime el mensaje
                System.out.println("ECO " + mensajeRecibido1 );

                // envia de vuelta el mismo mensaje
                serviCritor.println(" ECO  " + mensajeRecibido1);
            }


            socket.close();
            servidor.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
