package REPASO_RECU1;

import java.util.Random;

public class RelevosPSP {

    // Clase para la tarea, implementa Runnable
    static class Posta implements Runnable {
        private String nombreHilo;

        public Posta(String nombre) {
            this.nombreHilo = nombre;
        }

        @Override
        public void run() {
            // Generador de números aleatorios para el tiempo de espera
            Random random = new Random();

            System.out.println("\n*** " + nombreHilo + " ha tomado el testigo y comienza su posta. ***");

            for (int i = 1; i <= 5; i++) {
                System.out.println("Soy " + nombreHilo + ", iteración: " + i);

                try {
                    // 2. Dormir un tiempo aleatorio (100 a 500 ms)
                    int tiempoEspera = random.nextInt(401) + 100; // [0-400] + 100 = [100-500]
                    Thread.sleep(tiempoEspera);
                } catch (InterruptedException e) {
                    // Manejo de la interrupción del hilo
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println(nombreHilo + " ha terminado su posta.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Creación de los hilos
        Thread hilo1 = new Thread(new Posta("Hilo 1"), "H1");
        Thread hilo2 = new Thread(new Posta("Hilo 2"), "H2");
        Thread hilo3 = new Thread(new Posta("Hilo 3"), "H3");

        // --- INICIO DE LA CARRERA ---

        // a. Hilo 1 debe empezar primero
        hilo1.start();

        // ----------------------------------------------------
        // 3. SECUENCIALIDAD FORZADA CON JOIN()
        // ----------------------------------------------------

        // El hilo principal (main) espera a que hilo1 termine.
        System.out.println("Main esperando a que Hilo 1 termine...");
        hilo1.join();

        // b. Solo cuando Hilo 1 termina, Hilo 2 puede empezar.
        System.out.println("Hilo 1 terminado. Main lanza Hilo 2...");
        hilo2.start();

        // El hilo principal (main) espera a que hilo2 termine.
        System.out.println("Main esperando a que Hilo 2 termine...");
        hilo2.join();

        // c. Solo cuando Hilo 2 termina, Hilo 3 puede empezar.
        System.out.println("Hilo 2 terminado. Main lanza Hilo 3...");
        hilo3.start();

        // ----------------------------------------------------

        // 4. El programa principal no acaba hasta que los 3 hilos terminen.
        // Esperamos a Hilo 3 (que ya ha esperado indirectamente a H1 y H2).
        hilo3.join();

        System.out.println("\nCarrera de relevos terminada.");
    }
}