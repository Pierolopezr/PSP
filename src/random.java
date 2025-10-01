import java.io.IOException;

public class random {
    public static void main(String[] args) throws InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("sleep", "5");
        Process p = null;

        try {
            p = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Bucle sin bloquear: hacemos otra cosas mientras el hijo vive
        while (p.isAlive()) {
            System.out.println("El hijo vivo...");
            Thread.sleep(1000); //simulamos otras tareas
        }
        //cuando acaba, ya podemos consultar exitValue()
        System.out.println("El hijo terminó con código: " + p.exitValue());
    }
}
