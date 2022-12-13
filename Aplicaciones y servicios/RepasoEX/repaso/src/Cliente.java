import java.io.*;
import java.net.*;
import java.util.Random;


public class Cliente {
    public static void main(String[] args) throws UnknownHostException, IOException {
        try (Socket client = new Socket("127.0.0.1", 4321); // Abrimos una conexión con nuestra máquina en el puerto
            // 4321
            // Obtenemos un controlador de fichero de entrada/salida del socket
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            ObjectOutputStream OutputObject = new ObjectOutputStream(client.getOutputStream());
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) 
        {

            String entrada;

            System.out.println("Introduce comando: ");
            entrada = stdIn.readLine(); //leemos de teclado
            System.out.println("Comando enviado");
            out.println(entrada); //enviamos al servidor
        

            if(entrada.startsWith("NUEVA CUENTA: ")){
                    
                Random contador = new Random();
                Cuenta cuentaPersonal = new Cuenta(contador.nextInt());
                OutputObject.writeObject(cuentaPersonal);

                    
            }else if(entrada.startsWith("CUENTAS: ")){

                String[] separado=entrada.split("CUENTAS: ");
                String nombre = separado[1];
                
                String lista = in.readLine();
                System.out.println("Cuenta de " + nombre + ": ");
                System.out.println(lista);

            }else if(entrada.startsWith("ELIMINAR CUENTA: ")){

                String a = in.readLine();
                System.out.println(a);

            }


        } catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
            System.exit(1);
        }
    }
}
