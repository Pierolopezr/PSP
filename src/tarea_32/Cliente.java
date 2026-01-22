package tarea_32;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class Cliente {
    public static void main(String[] args) {
        int puerto_servidor = 8888;
        byte[] buffer = new byte[1024];
        String [] listaPalabras = {"arbol","perrocaliente","ferrocarril","peru",""};
        try{
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            // Se crea el DatagramSocket sin especificar un puerto, lo que hace que el sistema asigne un puerto aleatorio.
            // Esto es suficiente para recibir datagramas, ya que el servidor enviará los datos al puerto en el que el cliente esté escuchando.
            DatagramSocket datagramSocket = new DatagramSocket();


            String msj = Arrays.toString(listaPalabras);
            buffer = msj.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
            datagramSocket.send(pregunta);

            // Se crea un DatagramPacket con el tamaño de buffer especificado.
            // Este paquete se utilizará para recibir los datos enviados por el cliente.
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            // Al ejecutar este método, el buffer se llena con el mensaje enviado por el cliente.
            // Este método BLOQUEA la ejecución del código hasta que se reciba el paquete completo.
            // Si el mensaje enviado es más grande que el tamaño del buffer, el mensaje se truncará.
            datagramSocket.receive(peticion);
            System.out.println("Recibo msj del servidor");

            // Convierte el contenido del buffer recibido en un String para poder procesarlo.
            String msjServidor = new String(peticion.getData(), 0, peticion.getLength());
            System.out.println("msjServidor = " + msjServidor + " con una longitud de " + msjServidor.length());

        }catch (SocketException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
