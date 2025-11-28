package tarea_28;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Gestion_hilos extends  Thread{
    private Socket socket;

    public Gestion_hilos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        //try-with-resources cierra streams automáticamente
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serviCritor = new PrintWriter(socket.getOutputStream(), true);
            while (true){
                //Recibe el mensaje y lo lee
                String mensajeRecibido1 = lector.readLine();
                if(mensajeRecibido1.equalsIgnoreCase("adios")|| mensajeRecibido1 == null)break;

                // imprime el mensaje
                System.out.println("ECO " + mensajeRecibido1 );

                // envia de vuelta el mismo mensaje
                serviCritor.println(" ECO  " + mensajeRecibido1);
            }

        }catch (IOException e){
            System.out.println("Error con cliente: " + e.getMessage());
        }finally {
            try {
                socket.close();
                System.out.println("Cliente desconectado");
            }catch (IOException e){
                System.out.println("ERROR");
            }
        }

    }
}





