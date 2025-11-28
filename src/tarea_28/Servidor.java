package tarea_28;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
     static void main() {
        try(ServerSocket servidor = new ServerSocket()) {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);


            servidor.bind(dir); // bind (conectar) al puerto 6666

            System.out.println("Esperando conexiones");


            while(true){

                Socket socket = servidor.accept();
                System.out.println("Cliente conectado ");

                Gestion_hilos hilo = new Gestion_hilos(socket);
                hilo.start();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
