

public class tarea_12_HiloConPrioridad extends Thread{
    private String tipo;
    public tarea_12_HiloConPrioridad(String nombre, String tipo) {
        super(nombre);
        this.tipo  = tipo;
    }

    @Override
    public void run(){
        int i;
        int suma = 0;
        for(i= 0; i<=1923; i++){
            if ( tipo.equals("pares") && i % 2 == 0){
                suma += i;
                System.out.println(suma + " suma par");
            }
            if (tipo.equals("impares") && i % 2 != 0){
                suma += i;
                System.out.println(suma + " suma impar");
            }
            if (tipo.equals("personalizado") && (i % 10 == 3 || i % 10 == 4)){
                suma += i;
                System.out.println(suma + " suma terminada en 3 o 4");
            }

        }
        System.out.println("Hilo " + getName() + " (" + tipo + ") = " + suma);

    }


}
