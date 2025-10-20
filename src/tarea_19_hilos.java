// Clase donde se van a identificar las vocales
public class tarea_19_hilos extends Thread {
    private String vocal;
    private String texto;
    private tarea_19_contador contador; //referencia al contador compartido

    // Este es el constructor que recibe la vocal, el texto y el contador
    public tarea_19_hilos(String vocal, String texto, tarea_19_contador contador) {
        this.vocal = vocal;
        this.texto = texto;
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < texto.length(); i++) {
            if (String.valueOf(texto.charAt(i)).equalsIgnoreCase(vocal)) {
                contador.incrementar(); // Si coincide, aumenta el contador
            }
        }
    }
}
