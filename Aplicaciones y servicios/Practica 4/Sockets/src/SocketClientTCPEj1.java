import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientTCPEj1 {
    
    public static final int PUERTO = 4321;
     public static void main(String args[]) {
      
        try (
            // Abrimos una conexion con nuestra maquina en el puerto PUERTO
            Socket client = new Socket("127.0.0.1", PUERTO);

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) ;
            DataInputStream f = new DataInputStream(client.getInputStream());)

        {
            
            String nombFichero = "";
            
            do{
                System.out.println("Para salir escibe exit");
                System.out.println("Nombre del fichero: ");

                nombFichero = stdIn.readLine();

                String[] parts = nombFichero.split(" ");
                nombFichero = parts[1];

                System.out.println("Enviando " + nombFichero);

                out.println(nombFichero);

                try(FileOutputStream fichero = new FileOutputStream("CopiaServer" + nombFichero )){


                    int c;

                    while((c = f.read()) != -1){

                        fichero.write(c);

                    }

                    System.out.println("Listo");



                }catch(IOException e){

                System.out.println("Error en el cliente: " + e.getMessage());

                }
            }while(nombFichero != "exit");

            
        }catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " +
            e.getMessage());
        } 
    }    
}
