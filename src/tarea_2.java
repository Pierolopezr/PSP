import java.io.*;
import java.util.*;
public class tarea_2 {
    public static void main(String[] args) {
        try {
            // 1️⃣ Crear un ProcessBuilder para ejecutar un comando
            // En Linux/macOS usamos bash; en Windows cambiaría a "cmd /c"
            //ProcessBuilder pbuilder = new ProcessBuilder("/bin/bash", "-c", "echo $SALUDO");
            ProcessBuilder pbuilder = new ProcessBuilder("cmd", "/c", "echo %SALUDO%");
            // 2️⃣ Obtener las variables de entorno del proceso
            Map<String, String> entorno = pbuilder.environment();

            // 3️⃣ Crear una nueva variable de entorno llamada SALUDO
            entorno.put("SALUDO", "Hola Mundo");

            // 4️⃣ Iniciar el proceso
            Process proceso = pbuilder.start();

            // 5️⃣ Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Salida del proceso: " + linea);
            }

            // 6️⃣ Esperar a que el proceso termine
            proceso.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
