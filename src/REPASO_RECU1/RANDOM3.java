package REPASO_RECU1;
import java.util.Random;
public class RANDOM3 {
    static class Mantenimiento implements Runnable{

        private String nombreHilo;
        public Mantenimiento (String nombre){
            this.nombreHilo = nombre;
        }
        @Override
        public void run() {
            Random random = new Random();
            for(int i= 1; i<=6; i++){
                System.out.println("El " + nombreHilo + " iteración: " + i);
            }
            try {
                int tbloqueado = random.nextInt(251)+ 50;
                Thread.sleep(tbloqueado);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    static void main(String[] args) throws InterruptedException {
        Thread hilo1 = new Thread(new Mantenimiento("M1"), "MT1");
        Thread hilo2 = new Thread(new Mantenimiento("M2"), "MT2");
        Thread hilo3 = new Thread(new Mantenimiento("M3"), "MT3");
        Thread hilo4 = new Thread(new Mantenimiento("M4"), "MT4");

        hilo4.start();
        hilo4.join();
        hilo3.start();
        hilo3.join();
        hilo2.start();
        hilo2.join();
        hilo1.start();
        hilo1.join();

    }
}
