import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class tarea_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String os = System.getProperty("os.name").toLowerCase();
        while (true) {
// Pedir al usuario un comando
            System.out.println("Introduce un comando (o 'salir' para terminar) y sus parametros: ");
            String comando = scanner.nextLine().trim();

// Verificar si el usuario quiere salir
            if (comando.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa. Código de salida: 0");
                break; // Salir del bucle
            }
            ProcessBuilder pb;

            // 🔹 Cambio para hacerlo multiplataforma
            if (os.contains("win")) {
                // Windows: usar cmd /c para ejecutar comandos internos
                pb = new ProcessBuilder("cmd", "/c", comando);
            } else {
                // Linux/Mac: usar shell /bin/sh -c
                pb = new ProcessBuilder("/bin/sh", "-c", comando);
            }



            try {
// Iniciar el proceso
                Process proceso = pb.start();
                // Leer salida normal del comando
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }

                // Leer errores del comando
                BufferedReader errReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                while ((linea = errReader.readLine()) != null) {
                    System.err.println(linea);
                }
// Esperar a que el proceso termine y obtener el código de salida
                int exitCode = proceso.waitFor();

// Mostrar el nombre del comando y el código de finalización
                System.out.println("Comando ejecutado: " + comando);
                System.out.println("Código de finalización: " + exitCode);
                if (exitCode == 0) {
                    System.out.println("El comando se ejecutó correctamente.");
                } else {
                    System.out.println("El comando falló o no es válido.");
                }

            } catch (IOException e) {
                System.out.println("Error al ejecutar el comando: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("El proceso fue interrumpido: " + e.getMessage());
            }
        }


// Cerrar el escáner
        scanner.close();
    }
}