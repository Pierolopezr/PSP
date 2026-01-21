package tarea_32;

import java.io.IOException;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8888;
        byte[] buffer = new byte[1024];

        try{
            System.out.println("Servidor funcionando");

            // Creamos un datagramSocket y vinculamos al puerto especificado
            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            // Se crea un DatagramPacket con el tamaño de buffer especificado.
            // Este paquete se utilizará para recibir los datos enviados por el cliente.
            DatagramPacket peticion =  new DatagramPacket(buffer, buffer.length);

            // Al ejecutar este metodo, el buffer se llena con el mensaje enviado por el cliente.
            // Este metodo BLOQUEA la ejecución del código hasta que se reciba el paquete completo.
            // Si el mensaje enviado es más grande que el tamaño del buffer, el mensaje se truncará.
            datagramSocket.receive(peticion);
            System.out.println("Petición recibida");

            // Convertimos el buffer recibido en un string para procesarlo
            // analogia de la regla:
            // get data (cantidad de caracteres) offset(desde que caracter empiezo a leer el string), getlength(cuantos caracteres quieres que lea)
            String msj = new String(peticion.getData(), 0, peticion.getLength());
            System.out.println("msj: " + msj );

            msj=msj.replace("[","");
            msj=msj.replace("]",""); // el replace indica que reemplazará los [] por el vacio para que no cuente como caracter al momento de contar las palabras largas
            String[] palabras = msj.split(","); // el split indica que a partir de la coma es una palabra
            String palabraGrande = palabras[0];
            for (String palabra:palabras){ // por cada palabra dentro de palabras
                if (palabraGrande.length()<palabra.length()) palabraGrande = palabra;
            }
            // La información del cliente (dirección IP y puerto) está contenida en el DatagramPacket,
            // por lo que extraemos estos datos para poder enviarle una respuesta.
            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();

            // Envio una respuesta al cliente despues de haber extraido su direccion y puerto
            String msjServidor = palabraGrande;
            buffer = msjServidor.getBytes();
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
            datagramSocket.send(respuesta);
            System.out.println("Envié saludos al cliente");

        }catch (SocketException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
