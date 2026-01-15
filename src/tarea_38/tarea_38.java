package tarea_38;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class tarea_38 {
    public static void main(String[] args) {
        /*
        // Instanciar
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Cargar datos
        String texto = "";
        md.update(texto.getBytes());


        // Resumir (Devuelve bytes)
        byte[] resumen = md.digest();


        // Convertir a Hexadecimal (Legible)
        String hex = HexFormat.of().formatHex(resumen);
        System.out.println(hex); // Imprime: a5f3...
        */
        try{



        // FASE DE REGISTRO

        System.out.println("Hola, bienvenido. Crea una contraseña: ");
        Scanner op = new Scanner(System.in);
        String contrasena = op.nextLine();
        // Instanciar
        MessageDigest mp = MessageDigest.getInstance("SHA-256");
        // Cargar datos
        mp.update(contrasena.getBytes());
        // Resumir (Devuelve bytes)
        byte[] resumen = mp.digest();
        // Convertir a Hexadecimal (Legible)
        String hex2 = HexFormat.of().formatHex(resumen);


        // FASE DE LOGIN

        System.out.println("Usuario registrado. Inicie sesión para probar: ");
        String contrasena2 = op.nextLine();
        mp.reset(); // reutilizamos el objeto
        mp.update(contrasena2.getBytes()); // pasar la información en bytes. Si se usa varias veces la información, se acumula
        // Resumir (Devuelve bytes)
        byte[] resumen2 = mp.digest();
        // Convertir a Hexadecimal (Legible)
        String hex3 = HexFormat.of().formatHex(resumen2);


        // RESULTADO

        if (hex2.equals(hex3)){
            System.out.println("Acceso concedido");
        }else {
            System.out.println("ERROR: Credenciales inválidas");
        }
        }catch (NoSuchAlgorithmException e){
            System.out.println("Error de programa " + e.getMessage());
        }
    }
}
