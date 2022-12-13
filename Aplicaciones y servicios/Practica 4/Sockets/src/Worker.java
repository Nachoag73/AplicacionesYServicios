
import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker extends Thread{
    Socket cliente;

    // Constructor de la clase, recibe el socket
	public Worker(Socket so) {
		cliente = so;
	}

	// Ejecucion de la hebra que maneja la conexión
	public void run() {
	    try (
            // mover a la hebra
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            DataOutputStream f = new DataOutputStream(cliente.getOutputStream());)
        {
            
			System.out.println("Aceptada nueva conexion desde: " + Thread.currentThread().getName());
            String nombFichero = in.readLine();

            System.out.println("Recibido " + nombFichero);
            
            //Enviar contenido fichero

            try (FileInputStream fichero = new FileInputStream(nombFichero)){
                
                int c;

                while((c = fichero.read()) != -1){

                    f.write(c);

                }

                System.out.println("Datos enviados");

            }catch(IOException e){

                System.out.println("Error en el cliente: " + e.getMessage());

            }


	    } catch (IOException e) {
	        System.out.println(e);
	    }
	}
}
