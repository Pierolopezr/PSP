package tarea_38;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class tarea_38 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Instanciar
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Cargar datos
        String texto = "";
        md.update(texto.getBytes());


        // Resumir (Devuelve bytes)
        byte[] resumen = md.digest();


        // COnvertir a Hexadecimal (Legible)
        String hex = HexFormat.of().formatHex(resumen);
        System.out.println(hex); // Imprime: a5f3


    }
}
