public class tarea_12 {
    public static void main(String[] args) {
        int i;
        int suma = 0;
        for(i= 0; i<=5; i++){

            if (i % 2 == 0){
                suma += i;
                System.out.println(suma + " suma par");

            }

            if (i % 2 != 0){
                suma += i;
                System.out.println(suma + " suma impar");
            }

            if (i % 10 == 3 || i % 10 == 4){
                System.out.println(i + " iiiii");

            }



        }

    }
}
/*
Realiza un programa en java que ejecute tres hilos de forma concurrente.
Uno de ellos debe sumar los números pares del 1 al 1923,
otro los impares
, y otro, los que terminen en tres o en cuatro

for(int i= 0; i<=1923; i++)
 */
