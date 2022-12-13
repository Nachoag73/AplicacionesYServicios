import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void main(String[] args) {
        
        try (

            Socket client = new Socket("127.0.0.1", 4321);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); 
            BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream f = new DataOutputStream(client.getOutputStream());
            DataInputStream f2 = new DataInputStream(client.getInputStream());) //IMP

        {
            System.out.println("Que quieres hacer: Enviar o Recibir");

            String opcion = stdIn.readLine();
            out.println(opcion);

            if(opcion.equalsIgnoreCase("enviar")){


                System.out.print("Nombre archivo: ");

                String nombrearchivo = "";
    
                nombrearchivo = stdIn.readLine();
    
                out.println(nombrearchivo); //Envia al servidor el nombre del archivos;
                try (FileInputStream contenido = new FileInputStream(nombrearchivo);) 
                {
    
                    int c;
    
                    while ((c = contenido.read()) != -1) { //si devuelve -1 es fin de fichero
    
                        f.write(c);
                    }
    
    
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }

            }else if(opcion.equalsIgnoreCase("recibir")){

                System.out.print("Nombre archivo: ");

                String nombrearchivo = "";
    
                nombrearchivo = stdIn.readLine();
    
                out.println(nombrearchivo); //Envia al servidor el nombre del archivos;

                try (FileOutputStream copia = new FileOutputStream("Copiacliente"+ nombrearchivo);) 
                {
    
                    int c;
    
                    while ((c = f2.read()) != -1) { //si devuelve -1 es fin de fichero
    
                        copia.write(c);
                    }
    
    
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }

            }
            

        } catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " +
            e.getMessage());
            System.exit(1);
        }

    }
    
}
