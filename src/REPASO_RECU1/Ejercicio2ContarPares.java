package REPASO_RECU1;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio2ContarPares {

    // 1. RECURSO COMPARTIDO (CRÍTICO): Contador global atómico para el total
    private static AtomicInteger totalPares = new AtomicInteger(0);

    // 2. RECURSO COMPARTIDO (NO CRÍTICO): Mapa para almacenar las frecuencias.
    // Lo llenaremos al final. Cada hilo almacena su conteo localmente primero.
    private static Map<Integer, Integer> frecuencias = new HashMap<>();

    // Clase de Tarea: Cuenta un dígito par específico
    static class ContadorDigitoPar implements Runnable {
        private String texto;
        private final int digitoPar;
        private int conteoLocal = 0;

        public ContadorDigitoPar(String texto, int digito) {
            this.texto = texto;
            this.digitoPar = digito;
        }

        @Override
        public void run() {
            String digitoStr = String.valueOf(digitoPar);

            // Recorre el texto para encontrar el dígito asignado
            for (int i = 0; i < texto.length(); i++) {
                if (texto.substring(i, i + 1).equals(digitoStr)) {
                    conteoLocal++;
                }
            }

            // 3. SECCIÓN CRÍTICA: Actualizar el contador global
            // Añade el conteo local al total global de forma ATÓMICA y segura.
            totalPares.addAndGet(conteoLocal);

            // Guardamos la frecuencia local para la salida
            synchronized (frecuencias) {
                // Sincronizamos solo la escritura del mapa, ya que es un paso rápido.
                frecuencias.put(digitoPar, conteoLocal);
            }

            System.out.printf("Hilo para el dígito %d ha terminado, encontró: %d veces.\n", digitoPar, conteoLocal);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cadena de texto (ej. 123456789012):");
        String cadena = scanner.nextLine();
        scanner.close();

        // 4. Configuración de los hilos (uno por cada dígito par) [cite: 38]
        int[] digitosPares = {2, 4, 6, 8};
        Thread[] hilos = new Thread[digitosPares.length];

        System.out.println("----------------------------------------");
        System.out.println("Iniciando 4 hilos concurrentes...");

        // Creación y lanzamiento de los hilos
        for (int i = 0; i < digitosPares.length; i++) {
            ContadorDigitoPar tarea = new ContadorDigitoPar(cadena, digitosPares[i]);
            hilos[i] = new Thread(tarea, "Hilo-" + digitosPares[i]);
            hilos[i].start();
        }

        // 5. Esperar a que todos los hilos terminen con join()
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // 6. Mostrar resultados
        System.out.println("----------------------------------------");
        System.out.println("Resultados:");

        // Accedemos al total de forma segura
        System.out.println("Total de dígitos pares encontrados: " + totalPares.get());

        System.out.println("\nFrecuencia de cada dígito par:");
        // Imprimimos la frecuencia (usamos el mapa llenado por los hilos)
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            System.out.printf("Dígito %d: %d\n", entry.getKey(), entry.getValue());
        }
        System.out.println("Programa principal terminado.");
    }
}