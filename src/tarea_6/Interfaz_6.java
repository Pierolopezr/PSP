package tarea_6;

import java.util.Scanner;
/**
 * tarea 6 main
 * @author Piero López Rosas
 */
public class Interfaz_6 {

        public static void main(String[] args) {
            Scanner op = new Scanner(System.in);
            String entrada;

            // Bucle infinito que solo se romperá cuando el usuario escriba "salir"
            while (true) {
                System.out.println("Introduce el host o IP o 'salir' para finalizar:");
                entrada = op.nextLine();

                if (entrada.equalsIgnoreCase("salir")) {
                    System.out.println("Saliendo del programa...");
                    break;
                } else {
                    Lanzador_6.ejecutarPing(entrada); // llamamos a Lanzador
                }
            }

        }

}
