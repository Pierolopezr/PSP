package REPASO_RECU1;
import java.util.Random;
public class RANDOM {
    static class Secuencia implements Runnable {
        private String nombreHilo;

        public Secuencia (String nombre){
            this.nombreHilo = nombre;
        }
        @Override
        public void run() {
            Random random = new Random();

            for (int i = 1; i <= 8; i++){
                System.out.println("Soy el " + nombreHilo + " iteración: " + i );

                try {
                    int tiempoBloqueo = random.nextInt(401) + 100;
                    Thread.sleep(tiempoBloqueo);
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Creación de los hilos;
        Thread hilo1 = new Thread(new Secuencia("Hilo 1"), "H1");
        Thread hilo2 = new Thread(new Secuencia("Hilo 2"), "H2");
        Thread hilo3 = new Thread(new Secuencia("Hilo 3"), "H3");

        //Lanzo el hilo:
        hilo3.start();
        System.out.println("Esperando a que el hilo termine");
        hilo3.join();

        hilo2.start();
        System.out.println("Esperando a que el hilo termine");
        hilo2.join();

        hilo1.start();
        System.out.println("Esperando a que el hilo termine");
        hilo1.join();

    }

}
