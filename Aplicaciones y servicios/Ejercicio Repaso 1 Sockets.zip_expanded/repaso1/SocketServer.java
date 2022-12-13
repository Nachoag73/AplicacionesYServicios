package sockets.repaso1;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SocketServer {

    public static final int PUERTO = 4321;

    public static void main(String args[]) {
        
        // Inicializa el servidor en el socket PUERTO 
        try (ServerSocket serverSocket = new ServerSocket(PUERTO))
        {
            System.out.println("Servidor inicializado");
            System.out.println(serverSocket.getInetAddress());

            // Ejecuta un bucle infinito para aceptar nuevos clientes
            while (true) {
                try (
                Socket clientSocket = serverSocket.accept();     
                PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);                   
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("Aceptada nueva conexion");
                    String nombreFichero;
                    while ((nombreFichero = in.readLine()) != null) {
                        System.out.println("Recibido: " + nombreFichero);
                        enviarFichero(nombreFichero, out);
                        System.out.println("Enviado: " + nombreFichero);
                    }
                    out.flush();
                } catch (IOException e) {
                    System.out.println("Excepcion en una conexion con un cliente");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarFichero(String path, PrintWriter out) throws IOException {
        //long tam = Files.size(Paths.get(path)); //leer el tamanyo en bytes del fichero

        try(BufferedReader inputfile= new BufferedReader(new FileReader(path))){
            long numeroLineas = 0;
            try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
                numeroLineas = stream.count();
            }
            // escribimos la linea
            out.print(numeroLineas);
            out.println();
            
            // lenemos el fichero y lo escribimos
            String line;
            while ((line=inputfile.readLine())!=null){
                out.println(line);
            }
        }catch(FileNotFoundException e){
            out.println("El fichero no existe");
        }
        catch(Exception e){
            out.print("Error leyendo el fichero: "+e.getMessage());
        }
    }
}
