package tarea_11;

/**
 * Nivel 1 (obligatorio):
 * El objetivo de este nivel es implementar el mecanismo fundamental del efecto dominó, donde cada hilo "derriba" al siguiente. Para ello:
 * Realizar un programa que cree un hilo, que a su vez cree otro, y así consecutivamente hasta un total de cinco hilos (fichas).
 * Cada hilo debe cumplir rigurosamente con la siguiente secuencia de acciones:
 * Lanzar al siguiente hilo en la cadena (excepto el último).
 * Entrar en un bucle de 5 iteraciones. En cada una, debe imprimir su nombre y el número de iteración con un formato del estilo: Soy el hilo [nombre] iteración: [número].
 * Esperar un tiempo aleatorio (entre 100 y 600 ms) entre cada impresión del bucle.
 * Tras finalizar su bucle, esperar a que el siguiente hilo termine su ejecución por completo (simulando que no puede "asentarse" hasta que el resto de la cadena ha caído).
 * Finalmente, anunciar que ha finalizado con el formato del estilo: Acabó hilo [nombre].
 * @author Piero
 */
public class EfectoDomino extends Thread {

    int id; // número de ficha
    static final int TOTAL_HILOS = 5; // constante compartida para todos los hilos

    public EfectoDomino(int id) {
        this.id = id; // asigno el número de fichas al campo id
        setName("Ficha-" + id);
    }

    @Override
    public void run() {
        if (id < TOTAL_HILOS) {
            System.out.println(getName() + " empuja a la siguiente ficha");
            EfectoDomino siguiente = new EfectoDomino(id + 1);  // Creamos la siguiente ficha
            siguiente.start(); // lanzamos la siguiente ficha

            // Secuencia de la ficha actual
            for (int i = 1; i <= 5; i++) {
                System.out.println(getName() + " -> iteración " + i);
                try {
                    // Esperar un tiempo aleatorio entre 100 y 600 ms
                    int tiempoEspera = 100 + (int)(Math.random() * 501);
                    Thread.sleep(tiempoEspera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                // espero que la ficha o hilo termine para continuar
                System.out.println(getName() + " esperando a " + siguiente.getName());
                siguiente.join(); // bloqueo hasta que siguiente termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            // Última ficha solo hace su secuencia
            for (int i = 1; i <= 5; i++) {
                System.out.println(getName() + " -> iteración " + i);
                try {
                    int tiempoEspera = 100 + (int)(Math.random() * 501);
                    Thread.sleep(tiempoEspera);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(getName() + " acabó.");
    }


}
