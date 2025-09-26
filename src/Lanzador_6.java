import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * tarea 6 ejecutar ping
 * @author Piero López Rosas
 */
public class Lanzador_6 {
        public static void ejecutarPing(String host) {
            //host recibe el valor de entrada
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            // El comando ping es diferente en Windows y en Linux/Mac : https://axarnet.es/blog/ping-ip-windows-macos-linux
            if (os.contains("windows")) {
                // Estamos en Windows
                pb = new ProcessBuilder("ping", "-n", "4", host);
            } else {
                // Estamos en Linux, Mac, etc.
                pb = new ProcessBuilder("ping", "-c", "4", host);
            }

            try {

                 // Hago la salida del proceso ping se muestre en nuestra consola de Java en vez de una terminal.
                Process proceso = pb.start();
                // No uso inheritIO() ya que con eso solo se muestra en consola y no puedo procesarlo como con el bufferedReader.
                // Leer la salida del comando línea por línea
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String lineaLower = linea.toLowerCase();
                    if ( lineaLower.contains("tiempo") ){
                        System.out.println("[OK] " + linea);
                    } else if  (lineaLower.contains("tiempo de espera agotado") || lineaLower.contains("destino inaccesible")) {
                        System.out.println("[ERROR] " + linea);
                    } else {
                        System.out.println("[INFO] " + linea);
                    }
                }

                int codigoSalida = proceso.waitFor();  //espero aquí hasta que el proceso "ping" termine.

                System.out.println("Operación completada. Código de salida: " + codigoSalida); // Muestra el código de salida que nos ha devuelto el proceso.

            } catch (IOException | InterruptedException e) {
                // uso de los dos tipos de Exception
                // InterruptedException :  https://programmerclick.com/article/8830618963/
                // IOException : https://programmerclick.com/article/8830618963/
                System.err.println("Error al ejecutar el comando: " + e.getMessage());
            }
        }

}
