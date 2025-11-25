package REPASO_RECU1;

public class cafe {

    // La clase Cafeteria contiene el recurso compartido (el camarero)
    static class Cafeteria {
        // Recurso compartido: El estado del camarero
        private boolean camareroOcupado = false;

        // El método NO está sincronizado completamente para permitir liberar el candado durante el sleep.
        public void pedirCafe() throws InterruptedException {

            // --------------------------------------------------------
            // 1. BLOQUE Sincronizado (Adquisición del Recurso)
            // --------------------------------------------------------
            // El hilo toma el candado 'this' para chequear la condición y modificar 'camareroOcupado'.
            synchronized (this) {

                // Si el camarero está ocupado, el cliente espera (liberando el candado).
                // USAR 'while' es CRUCIAL para re-chequear la condición al despertar.
                while (camareroOcupado) {
                    System.out.println("-> " + Thread.currentThread().getName() + " esperando en cola.");
                    wait(); // Se duerme y LIBERA el candado 'this'.
                }

                // Una vez despierto o libre: tomamos el recurso
                camareroOcupado = true;
                System.out.println(Thread.currentThread().getName() + " -> Pedido en marcha. Camarero ocupado.");

            } // 🔑 CANDADO LIBERADO aquí.

            // --------------------------------------------------------
            // 2. OPERACIÓN LARGA (Tiempo de Trabajo - SIN CANDADO)
            // --------------------------------------------------------

            // Simulamos el tiempo que tarda en hacer el café.
            // **IMPORTANTE:** El hilo duerme, pero el candado NO está ocupado,
            // permitiendo la alta eficiencia y concurrencia.
            Thread.sleep(2000);

            // --------------------------------------------------------
            // 3. BLOQUE Sincronizado (Liberación del Recurso y Notificación)
            // --------------------------------------------------------
            // El hilo vuelve a tomar el candado para modificar el estado y notificar.
            synchronized (this) {

                System.out.println("<- " + Thread.currentThread().getName() + " ha recibido su café.");

                // Liberamos el recurso
                camareroOcupado = false;

                // Avisamos a todos los clientes que están en espera (wait())
                notifyAll();
            } // 🔑 CANDADO LIBERADO aquí.
        }
    }

    // La clase Cliente (El Hilo de Tarea)
    static class Cliente extends Thread {
        private Cafeteria cafeteria;

        public Cliente(Cafeteria cafeteria, String name) {
            super(name);
            this.cafeteria = cafeteria;
        }

        @Override
        public void run() {
            try {
                cafeteria.pedirCafe();
            } catch (InterruptedException e) {
                // Manejo de interrupción
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Cafeteria cafeteria = new Cafeteria();

        System.out.println("Iniciando la cafetería con 5 clientes...");

        // Creamos y lanzamos los 5 hilos
        new Cliente(cafeteria, "Cliente 1").start();
        new Cliente(cafeteria, "Cliente 2").start();
        new Cliente(cafeteria, "Cliente 3").start();
        new Cliente(cafeteria, "Cliente 4").start();
        new Cliente(cafeteria, "Cliente 5").start();
    }
}