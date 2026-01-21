package Datagram_Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class d_servidor {

    public static void main(String[] args) {
        int puerto = 6666;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Servidor arrancando");

            // El constructor crea un DatagramSocket y lo asocia (o "vincula") al puerto especificado.
            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            // Se crea un DatagramPacket con el tamaño de buffer especificado.
            // Este paquete se utilizará para recibir los datos enviados por el cliente.
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            // Al ejecutar este metodo, el buffer se llena con el mensaje enviado por el cliente.
            // Este metodo BLOQUEA la ejecución del código hasta que se reciba el paquete completo.
            // Si el mensaje enviado es más grande que el tamaño del buffer, el mensaje se truncará.
            datagramSocket.receive(peticion);
            System.out.println("Recibida peticion");

            // Convierte el contenido del buffer recibido en un String para poder procesarlo. // offset : para marcar de donde quieres empezar a leer el string, // analogia de la regla, get data (cantidad de caracteres) offset(desde que caracter), getlength(hasta que punto quieres que lea)
            String msj = new String(peticion.getData(),0 , peticion.getLength());
            System.out.println("msj = " + msj + msj.length());

            // La información del cliente (dirección IP y puerto) está contenida en el DatagramPacket,
            // por lo que extraemos estos datos para poder enviarle una respuesta.
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();

            // Envio la respuesta al cliente utilizando su dirección IP y puerto extraidos previamente.
            String msjServidor = "Saludos de su servidor";
            buffer = msjServidor.getBytes();
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
            datagramSocket.send(respuesta);
            System.out.println("Envié saludos al cliente");

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}