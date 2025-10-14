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
        tarea_19_hilos v1 = new tarea_19_hilos("a");
        tarea_19_hilos v2 = new tarea_19_hilos("b");
        tarea_19_hilos v3 = new tarea_19_hilos("c");
        tarea_19_hilos v4 = new tarea_19_hilos("d");
        tarea_19_hilos v5 = new tarea_19_hilos("e");

        v1.start();
        v2.start();
        v3.start();
        v4.start();
        v5.start();

        Scanner op = new Scanner(System.in);
        System.out.println("Escribe una palabra y yo te detectaré las vocales");

    }

}
