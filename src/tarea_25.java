import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Usando la clase java.net.Socket:
 * Crea un programa que pida al usuario una dirección IP (o localhost).
 * El programa deberá intentar conectarse creando un socket a los siguientes puertos "famosos": 21 (FTP), 22 (SSH), 80 (HTTP) y 443 (HTTPS)  o al puerto que le indique el usuario.
 * Debe imprimir por pantalla un resumen, indicando qué puertos están abiertos y cuáles cerrados o el que haya indicado el usuario.
 * El programa estará en un bucle realizando el paso 1 y 2 hasta que el usuario indique "salir".
 * @author Piero
 */


public class tarea_25 {
    public static void main(String[] args) {
        int puerto, opcion;
        String host;

        Scanner op = new Scanner(System.in);
        System.out.println("Dame una dirección ip o localhost: ");
        host = op.nextLine();
        while(true){

            System.out.println("¿A qué puerto quieres conectarte?: 1:21 (FTP), 2:22 (SSH), 3:80 (HTTP), 4:443 (HTTPS), 5: otro y 6: salir");
            opcion = op.nextInt();

            switch (opcion){
                case 1-> puerto = 21;
                case 2-> puerto = 22;
                case 3-> puerto = 80;
                case 4-> puerto = 443;
                case 5-> {
                    System.out.println("Elije el puerto a conectar: ");
                    puerto = op.nextInt();
                }
                default-> puerto = -2;
            }
            if (puerto==-2) break;

            try {
                Socket enchufe = new Socket(host, puerto);
                if(enchufe.isConnected()){ //Ve el estado el socket,  si el socker está conectado -> entonces ...
                    System.out.println(" El puerto " + puerto + " está abierto" );
                }
                enchufe.close();
            // Si la dirección IP no se pudo determinar
            }catch (UnknownHostException e){
                System.out.println("La dirección IP no se pudo determinar " + e.getMessage());
            }catch (IOException e){ // si el socker no está conectado -> entonces ...
                System.out.println("El puerto " + puerto + " está cerrado");
            }
        }
    }
}
