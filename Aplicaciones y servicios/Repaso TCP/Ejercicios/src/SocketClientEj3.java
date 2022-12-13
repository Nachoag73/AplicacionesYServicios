import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientEj3 {
    
    public static final int PUERTO = 4321;
     public static void main(String args[]) {
      
        try (
            // Abrimos una conexion con nuestra maquina en el puerto PUERTO
            Socket client = new Socket("127.0.0.1", PUERTO);

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) ;
            DataOutputStream f = new DataOutputStream(client.getOutputStream());)

        {
            String nombrefich ="";

            nombrefich = stdIn.readLine();
            out.println(nombrefich);
            System.out.println("El fichero se llama: " + nombrefich);


            try(FileInputStream inf = new FileInputStream(nombrefich)){

                int c;

                while((c = inf.read()) != -1){

                    f.write(c);

                }

                System.out.println("Enviado nombre y datos");


            }catch(IOException e){

                System.out.println("Error en el cliente: " + e.getMessage());

            }

            f.flush();
            
        }catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " +
            e.getMessage());
        } 
    }    
}
