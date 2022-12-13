import java.io.*;
import java.net.*;


public class SocketServerEj3 {

    public static final int PUERTO = 4321;

    public static void main(String args[]) {
        
        // Inicializa el servidor en el socket PUERTO 
        try (ServerSocket serverSocket = new ServerSocket(PUERTO))
        {
            System.out.println("Servidor inicializado");
            System.out.println(serverSocket.getInetAddress());
            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();     
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    DataInputStream f = new DataInputStream(clientSocket.getInputStream());

                ) {

                    System.out.println("Aceptada nueva conexion");
                    String nombreFichero;

                    while ((nombreFichero = in.readLine()) != null) {
                        System.out.println("Recibido: " + nombreFichero);

                        try(FileOutputStream outf = new FileOutputStream("Copia"+nombreFichero)){

                            int datos;
            
                            while((datos = f.read()) != -1){
            
                                outf.write(datos);
            
                            }
            
                            System.out.println("Copiado nombre y datos");
            
            
                        }catch(IOException e){
            
                            System.out.println("Error en el cliente: " + e.getMessage());
            
                        }
    
                    }
                } catch (IOException e) {
                    System.out.println("Excepcion en una conexion con un cliente");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}