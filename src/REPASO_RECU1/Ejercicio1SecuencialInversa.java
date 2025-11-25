package REPASO_RECU1;

import java.util.Random;

public class Ejercicio1SecuencialInversa {

    // Clase de Tarea: El Hilo que realiza la acción
    static class TareaSecuencial implements Runnable {
        private String nombreHilo;

        public TareaSecuencial(String nombre) {
            this.nombreHilo = nombre;
        }

        @Override
        public void run() {
            Random random = new Random();

            System.out.println("\n[INICIA] Soy el " + nombreHilo + ".");

            // El hilo escribe 8 veces su nombre y la iteración [cite: 24]
            for (int i = 1; i <= 8; i++) {
                System.out.println("Soy el " + nombreHilo + " iteración: " + i);

                try {
                    // Bloqueo durante un tiempo aleatorio [cite: 25]
                    int tiempoBloqueo = random.nextInt(401) + 100; // 100ms a 500ms
                    Thread.sleep(tiempoBloqueo);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("[FINALIZA] El " + nombreHilo + " ha terminado su ejecución.");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 1. Creación de los hilos
        Thread hilo1 = new Thread(new TareaSecuencial("Hilo 1"), "H1");
        Thread hilo2 = new Thread(new TareaSecuencial("Hilo 2"), "H2");
        Thread hilo3 = new Thread(new TareaSecuencial("Hilo 3"), "H3");

        // --- INICIO DE LA SECUENCIA FORZADA (H3 -> H2 -> H1) ---

        // 2. Lanzar Hilo 3 (el primero en ejecutarse)
        hilo3.start();

        // El hilo 'main' espera a que Hilo 3 acabe.
        System.out.println("Main: Esperando a que Hilo 3 termine...");
        hilo3.join();

        // 3. Cuando Hilo 3 acaba, lanzamos Hilo 2
        System.out.println("Main: Hilo 3 acabó. Lanzando Hilo 2...");
        hilo2.start();

        // El hilo 'main' espera a que Hilo 2 acabe.
        System.out.println("Main: Esperando a que Hilo 2 termine...");
        hilo2.join();

        // 4. Cuando Hilo 2 acaba, lanzamos Hilo 1
        System.out.println("Main: Hilo 2 acabó. Lanzando Hilo 1...");
        hilo1.start();

        // 5. El programa principal no acaba hasta que no terminen los 3 hilos [cite: 27]
        // Esperamos a Hilo 1 (el último)
        hilo1.join();

        System.out.println("\nPrograma principal terminado.");
    }
}