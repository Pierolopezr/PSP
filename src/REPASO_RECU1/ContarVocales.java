package REPASO_RECU1;

import java.util.concurrent.atomic.AtomicInteger;

public class ContarVocales {

    // 1. RECURSO COMPARTIDO Y CRÍTICO: Contador global
    // Usamos AtomicInteger para garantizar que la suma sea SEGURA y ATÓMICA.
    private static AtomicInteger totalVocales = new AtomicInteger(0);

    // Clase de la Tarea: Cuenta una vocal específica en el texto
    static class ContadorVocal implements Runnable {
        private String texto;
        private char vocal;
        private int conteoLocal = 0;

        public ContadorVocal(String texto, char vocal) {
            this.texto = texto.toLowerCase(); // Trabajar en minúsculas para simplificar
            this.vocal = vocal;
        }

        @Override
        public void run() {
            // Recorre el texto (cada hilo hace esto de forma CONCURRENTE)
            for (int i = 0; i < texto.length(); i++) {
                if (texto.charAt(i) == vocal) {
                    conteoLocal++;
                }
            }

            // Muestra su resultado individual
            System.out.printf("El hilo para '%c' ha encontrado: %d veces.\n", vocal, conteoLocal);

            // 4. Actualización del contador global compartido (Sección Crítica)
            // Usamos addAndGet() para sumar el total del hilo de forma ATÓMICA.
            totalVocales.addAndGet(conteoLocal);
            // Esto es seguro y mucho más rápido que usar synchronized.
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Cadena de prueba
        String cadena = "Programacion de Servicios y Procesos, Examen PSP. Esto es una prueba.";

        // Vocales a contar
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};
        Thread[] hilos = new Thread[vocales.length];

        System.out.println("Cadena a analizar: \"" + cadena + "\"");
        System.out.println("----------------------------------------");

        // 2. Creación y lanzamiento de un hilo por cada vocal
        for (int i = 0; i < vocales.length; i++) {
            // Creamos la tarea con el texto y la vocal
            ContadorVocal tarea = new ContadorVocal(cadena, vocales[i]);

            // Creamos el hilo y le asignamos un nombre descriptivo
            hilos[i] = new Thread(tarea, "Hilo-" + vocales[i]);
            hilos[i].start();
        }

        // 5. Esperar a que todos los hilos terminen usando join()
        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("----------------------------------------");
        // 5. Mostrar el total final (resultado seguro gracias a AtomicInteger)
        System.out.println("Total de vocales encontradas: " + totalVocales.get());
        System.out.println("Programa finalizado.");
    }
}