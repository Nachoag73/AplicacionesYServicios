package sockets.repaso1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    
    public static final int PUERTO = 4321;
     public static void main(String args[]) {
      
        try (
            // Abrimos una conexion con nuestra maquina en el puerto PUERTO
            Socket client = new Socket("127.0.0.1", PUERTO);
            
            // Obtenemos un controlador de fichero de entrada/salida del socket
            PrintWriter out =
                new PrintWriter(client.getOutputStream(), true);
           
                BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
            ) 
        {
            String inputKeyboard="", inputSocket ;
            long lines;
            int i=0;
            while ((inputKeyboard = stdIn.readLine()) != null 
            && !inputKeyboard.equals("exit")) {
                out.println(inputKeyboard);
                try{
                    lines = Long.parseLong(in.readLine()); // hacer conversion a float
                    i=0;
                    System.out.println("Empezamos a recibir fichero "+inputKeyboard +" con numero de lineas: "+lines);
                    while( i<lines && (inputSocket=in.readLine())!=null){
                        System.out.println(inputSocket);
                        i++;
                    }
                    System.out.println("Recibido fichero "+inputKeyboard);
                }catch (Exception e){
                    System.out.println("Error en el cliente: "+e.getMessage());
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " +
            e.getMessage());
        } 
    }    
}
