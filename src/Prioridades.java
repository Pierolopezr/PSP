public class Prioridades {
    public static void main(String[] args) {
        HiloConPrioridad h1 = new HiloConPrioridad("Baja prioridad");
        h1.setPriority(Thread.MIN_PRIORITY);

        HiloConPrioridad h2 = new HiloConPrioridad("Alta prioridad");
        h2.setPriority(Thread.MAX_PRIORITY);

        h1.start();
        h2.start();
    }
}
