package tarea_19;

public class tarea_19_contador { //Clase que tiene el contador compartido entre los hilos
    private int count = 0; // count variable con el total de vocales

    // Método sincronizado para incrementar el contador de forma segura
    public synchronized void incrementar() {
        count++;
    }

    // Método para obtener el valor actual del contador
    public int getCount() {
        return count;
    }
}
