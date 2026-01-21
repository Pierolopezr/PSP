package tarea_32;

import java.io.IOException;
import java.net.SocketException;

public class Cliente {
    public static void main(String[] args) {
        int puerto_servidor = 8888;
        byte[] buffer = new byte[1024];

        try{

        }catch (SocketException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
