public class HiloConPrioridad extends Thread{
    public HiloConPrioridad(String nombre) {
        super(nombre);
    }
    public void run(){
        System.out.println("SOY " + getName());
    }
}

