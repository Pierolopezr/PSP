import java.io.IOException;
import java.util.Scanner;
public class tarea_5 {
    public static void main(String[] args) {
        /**
         * Crea un programa en Java que esté en bucle realizando lo siguiente:
         *
         * 1. Pide por consola al usuario un comando y sus parámetros a ejecutar (si fuese necesario) (Por ejemplo, ls, gnome-text-editor, open...)
         * 2. Lanza el proceso y obtén el código de finalización del mismo.
         * 3. Muestra el nombre del programa y el código de finalización del mismo.
         *
         * El programa finaliza cuando un usuario introduce “salir” y devolverá un código de salida = 0. Prueba a introducir comandos y/o parámetros inexistentes y observa el código de finalización.
         * @author Piero López Rosas
         */
        Scanner op = new Scanner(System.in);
        System.out.println("¿Cuál es el nombre de tu archivo a abrir?");
        String notas = op.nextLine();
        ProcessBuilder pBloc = new ProcessBuilder("ls", notas);
        Process p;
        try {
            p = pBloc.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Bucle sin bloquear: hacemos otras cosas mientras el hijo vive
        while (p.isAlive()){
            System.out.println("El hijo sigue vivo...");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("El hijo terminó con código: " + p.exitValue());
    }
}
