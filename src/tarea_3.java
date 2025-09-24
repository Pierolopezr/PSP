import java.io.IOException;
import java.util.Scanner;
import java.lang.ProcessBuilder;
/**
 * @author Piero López Rosas
 * Creación de procesos con ProcessBuilder mediante un bloc de notas
 */

public class tarea_3 {
    public static void main(String[] args) throws IOException {
        Scanner op = new Scanner(System.in);
        System.out.println("¿Cuál es el nombre de tu archivo a abrir?");
        String notas = op.nextLine();
        ProcessBuilder pBloc = new ProcessBuilder("notepad", notas);
        // gnome-text-editor
        Process p = pBloc.start();
    }
}
