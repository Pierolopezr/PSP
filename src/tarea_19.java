import java.util.Scanner;

/**
 * Crea un programa en Java que utilice 5 hilos que representan el número de vocales totales.
 * Para contar el número de vocales que hay en un determinado texto (que puede ser introducido por teclado o estar en un fichero).
 * Cada hilo se encargará de contar una vocal diferente, actualizando todos los hilos una misma variable que indica el total de vocales del texto.
 * Los hilos se deben ejecutar concurrentemente.
 * @author Piero López
 */
public class tarea_19 {
    public static void main(String[] args) {
        Scanner op = new Scanner(System.in);
        String yn; // variable para controlar si se repite el programa

        while (true) {
            System.out.println("Escribe una palabra y yo te detectaré las vocales");
            String texto = op.nextLine();

            tarea_19_contador contador = new tarea_19_contador(); // creo el contenido compartido

            // Creo los 5 hilos y los inicio con el v''.start()
            tarea_19_hilos v1 = new tarea_19_hilos("a", texto, contador);
            tarea_19_hilos v2 = new tarea_19_hilos("e", texto, contador);
            tarea_19_hilos v3 = new tarea_19_hilos("i", texto, contador);
            tarea_19_hilos v4 = new tarea_19_hilos("o", texto, contador);
            tarea_19_hilos v5 = new tarea_19_hilos("u", texto, contador);

            v1.start();
            v2.start();
            v3.start();
            v4.start();
            v5.start();

            // Se usan hilos de forma "concurrente" para contar vocales de forma segura.

            try {
                v1.join();
                v2.join();
                v3.join();
                v4.join();
                v5.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Total de vocales encontradas: " + contador.getCount());

            while (true) {
                System.out.println("¿Quieres volver a introducir otra palabra? (y/n)");
                yn = op.nextLine();

                if (yn.equals("y")) {
                    break; // sale del bucle interno y repite el programa
                } else if (yn.equals("n")) {
                    System.out.println("¡Chaufaa!");
                    return; // termina el programa
                } else {
                    System.out.println("No te entendí");
                }
            }
        }
    }
}
