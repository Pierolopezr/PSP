public class tarea_12 {
    public static void main(String[] args) {
        tarea_12_HiloConPrioridad h1 = new tarea_12_HiloConPrioridad("Hilo", "pares");
        tarea_12_HiloConPrioridad h2 = new tarea_12_HiloConPrioridad("Hilo", "impares");
        tarea_12_HiloConPrioridad h3 = new tarea_12_HiloConPrioridad("HIlo","personalizado");

        h1.start();
        h2.start();
        h3.start();

    }
}
/*
Realiza un programa en java que ejecute tres hilos de forma concurrente.
Uno de ellos debe sumar los números pares del 1 al 1923,
otro los impares
, y otro, los que terminen en tres o en cuatro

for(int i= 0; i<=1923; i++)


 */
