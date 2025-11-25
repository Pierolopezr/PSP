package REPASO_RECU1;
// Define el paquete (carpeta) donde se encuentra la clase.

import java.util.Random;
// Importa la clase Random, necesaria para generar números aleatorios (el tiempo de espera).

public class RANDOM2 {
// Define la clase principal que contiene la lógica del programa concurrente.

    static class secuencia2 implements Runnable {
        // Clase interna que define la TAREA que realizarán los hilos.
        // Implementa 'Runnable' (la interfaz de tarea), indicando que esta clase puede ser ejecutada por un hilo.
        private String nombreHilo;
        // Variable local para almacenar el nombre que se le asigna a cada instancia de la tarea (ej. "Hilo 1").

        public secuencia2(String nombre) {
            // Constructor de la tarea.
            this.nombreHilo = nombre;
            // Asigna el nombre proporcionado a la variable de instancia.
        }

        @Override
        // Indica que este método sobrescribe el método 'run' de la interfaz Runnable.
        public void run() {
            // Método que contiene la lógica que el hilo ejecutará al llamar a 'start()'.
            Random random = new Random();
            // Crea un objeto para generar números aleatorios.

            for (int i = 1; i <= 5; i++) {
                // Inicia un bucle que se repetirá 5 veces (las 5 iteraciones de la posta/relevo).
                System.out.println("Soy " + nombreHilo + " , iteración: " + i);
                // Imprime el nombre del hilo actual y el número de iteración (Salida de la tarea).
                try {
                    // Inicia un bloque 'try-catch' para manejar posibles interrupciones del hilo.
                    int tiempo = random.nextInt(401) + 100;
                    // Genera un tiempo de espera aleatorio entre 100 ms y 500 ms (400+100).
                    Thread.sleep(tiempo);
                    // 🛑 Bloquea la ejecución del hilo ACTUAL por el tiempo generado (simula trabajo).
                } catch (InterruptedException e) {
                    // Captura la excepción si el hilo es interrumpido mientras está dormido.
                    Thread.currentThread().interrupt();
                    // Restablece el estado de interrupción del hilo (buena práctica de PSP).
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        // Método principal del programa. La cláusula 'throws InterruptedException' es necesaria por el 'join()'.

        // 1. Creación de los objetos Hilo (Thread) y asignación de la Tarea (secuencia2)
        Thread hilo1 = new Thread(new secuencia2("Hilo 1"), "H1");
        // Crea el objeto Hilo 1, asignándole una nueva tarea y el nombre interno "H1".
        Thread hilo2 = new Thread(new secuencia2("Hilo 2"), "H2");
        // Crea el objeto Hilo 2.
        Thread hilo3 = new Thread(new secuencia2("Hilo 3"), "H3");
        // Crea el objeto Hilo 3.

        // 2. Ejecución secuencial forzada (H1 -> H2 -> H3)

        hilo1.start();
        // 🔑 INICIA la ejecución del Hilo 1. Pasa de estado 'New' a 'Runnable'.

        hilo1.join();
        // 🛑 El hilo principal (main) se bloquea aquí, esperando a que Hilo 1 termine completamente.

        System.out.println("Hilo 1 ha terminado su posta");
        // Este mensaje se imprime SÓLO después de que Hilo 1 haya finalizado.

        hilo2.start();
        // 🔑 INICIA la ejecución del Hilo 2.

        hilo2.join();
        // 🛑 El hilo principal (main) se bloquea aquí de nuevo, esperando a que Hilo 2 termine.

        // Nota: Aquí faltaría un mensaje de "Hilo 2 ha terminado su posta" para mayor claridad.

        hilo3.start();
        // 🔑 INICIA la ejecución del Hilo 3.

        hilo3.join();
        // 🛑 El hilo principal (main) se bloquea aquí por última vez, esperando a que Hilo 3 termine.

        System.out.println("Carrera de relevos terminada");
        // Mensaje final que se imprime SÓLO después de que Hilo 3 haya finalizado.

    } // Fin del programa principal.
}