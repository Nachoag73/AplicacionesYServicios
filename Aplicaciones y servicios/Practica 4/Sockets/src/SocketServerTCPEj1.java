
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerTCPEj1 {

    public static final int PUERTO = 4321;

    public static void main(String args[]) {

        // Inicializa el servidor en el socket PUERTO
        try (ServerSocket serverSocket = new ServerSocket(PUERTO))
        {
            ExecutorService poolHebras = Executors.newFixedThreadPool(10);

            System.out.println("Servidor inicializado");
            System.out.println(serverSocket.getInetAddress());

            // Ejecuta un bucle infinito para aceptar nuevos clientes
            while (!Thread.currentThread().isInterrupted()) {
                try {

                    Socket clientSocket = serverSocket.accept(); //acepta un cliente;
                    poolHebras.submit(new Worker(clientSocket));

                } catch (IOException e) {
                    System.out.println("Excepcion en una conexion con un cliente");
                    System.out.println(e.getMessage());
                }
            }
            
            poolHebras.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
