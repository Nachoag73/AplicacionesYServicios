import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        
        try (ServerSocket serverSocket = new ServerSocket(4321);)
        {
            System.out.println("Servidor inicializado");

            while(true){

                try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                    DataInputStream f = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream f2 = new DataOutputStream(clientSocket.getOutputStream());
                ){

                    String opcion = in.readLine();

                    if(opcion.equalsIgnoreCase("enviar")){

                        opcion = "recibir";

                    }else if(opcion.equalsIgnoreCase("recibir")){

                        opcion = "enviar";

                    }

                    if(opcion.equalsIgnoreCase("recibir")){

                        String nombrearchivo = in.readLine(); //recibe el nombre del archivo

                        try (FileOutputStream copia = new FileOutputStream("Copiaserver "+nombrearchivo)){
        
                            int c;
        
                            while((c = f.read()) != -1){
        
                                copia.write(c);
        
                            }
                            
                        }catch (IOException e) {
                            e.printStackTrace(System.out);
                        }

                    }else if(opcion.equalsIgnoreCase("enviar")){


                        String nombrearchivo = in.readLine(); //recibe el nombre del archivo

                        try (FileInputStream contenido = new FileInputStream(nombrearchivo);) 
                        {
            
                            int c;
            
                            while ((c = contenido.read()) != -1) { //si devuelve -1 es fin de fichero
            
                                f2.write(c);
                            }
            
            
                        } catch (IOException e) {
                            e.printStackTrace(System.out);
                        }

                    }


                }catch (IOException e) {

                    System.out.println("Excepcion en una conexion con un cliente");
                    System.out.println(e.getMessage());

                }
            }

        }catch (IOException e) {

            e.printStackTrace();
        
        }

    }
}
