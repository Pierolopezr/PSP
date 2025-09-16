import java.io.IOException;
import java.util.Scanner;
/**
 * @author Piero López Rosas
 * Creación de procesos con ProcessBuilder mediante un bloc de notas
 */

public class tarea_3 {
    public static void main(String[] args) throws IOException {
        Scanner op = new Scanner(System.in);
        System.out.println("¿Cuál es el nombre de tu archivo a abrir?");
        String archivo = op.nextLine();
        String notas = "prueba.txt";
        ProcessBuilder pBloc = new ProcessBuilder("gnome-text-editor", notas);
        Process p;
        if (archivo == notas){
            pBloc.start();
        }
        else {
            System.out.println("Archivo no encontrado");
        }
    }
}
// Scanner op = New Scanner(System.in);
//