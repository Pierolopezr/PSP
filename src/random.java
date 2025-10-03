import java.io.IOException;

public class random {
    public static void main(String[] args) throws InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        if (os.contains("win")) {
            pb = new ProcessBuilder("cmd", "/c", "timeout", "/T", "5");
        } else {
            pb = new ProcessBuilder("sleep", "5");
        }

        Process p = null;
        try {
            p = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Bucle sin bloquear: hacemos otra cosa mientras el hijo vive
        while (p.isAlive()) {
            System.out.println("El hijo vivo...");
            Thread.sleep(1000); //simulamos otras tareas
        }
        //cuando acaba, ya podemos consultar exitValue()
        System.out.println("El hijo terminó con código: " + p.exitValue());
    }
}
