package tarea_38;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class tarea_38 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        /*
        // Instanciar
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Cargar datos
        String texto = "";
        md.update(texto.getBytes());


        // Resumir (Devuelve bytes)
        byte[] resumen = md.digest();


        // COnvertir a Hexadecimal (Legible)
        String hex = HexFormat.of().formatHex(resumen);
        System.out.println(hex); // Imprime: a5f3...
        */





        // FASE DE REGISTRO
        String contrasena = "";
        System.out.println("Hola, bienvenido. Crea una contraseña: ");
        Scanner op = new Scanner(System.in);
        contrasena = op.nextLine();

        // Instanciar
        MessageDigest mp = MessageDigest.getInstance("SHA-256");
        // Cargar datos
        mp.update(contrasena.getBytes());

        // Resumir (Devuelve bytes)
        byte[] resumen2 = mp.digest();


        // COnvertir a Hexadecimal (Legible)
        String hex2 = HexFormat.of().formatHex(resumen2);
        System.out.println(hex2);

        // FASE DE LOGIN
        System.out.println("Usuario registrado. Inicie sesión para probar: ");
        String contrasena2 = op.nextLine();

        if (contrasena2.equals(contrasena)){
            System.out.println("Acceso concedido");
        }else {
            System.out.println("ERROR: Credenciales inválidas");
        }

    }
}
