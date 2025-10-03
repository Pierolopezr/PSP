/**
 * EJERCICIO 11
 * @author Piero
 */
public class DOMINO {
    public static void main(String[] args) {
        System.out.println("======= INICIO DEL EFECTO DOMINÓ =======");

        // Creamos y empujamos la primera ficha para que comience la reacción en cadena.
        new EfectoDomino(1).start();
    }
}
