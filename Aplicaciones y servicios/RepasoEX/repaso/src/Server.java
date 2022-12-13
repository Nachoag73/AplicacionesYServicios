import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(4321);){
            System.out.println("Servidor inicializado");

            Map<String, Set<Cuenta>> mapaCuentas = new HashMap<String, Set<Cuenta>>();

            while(true){
                try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    ObjectInputStream inputObject = new ObjectInputStream(clientSocket.getInputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    ObjectOutputStream OutputObject = new ObjectOutputStream(clientSocket.getOutputStream());)
                { 
                    System.out.println("Aceptada nueva conexion");

                    String entrada = in.readLine();
                    System.out.println("El comando introducido es : " + entrada);
                    String[] separado;

                    if(entrada.startsWith("NUEVA CUENTA: ")){
                        separado = entrada.split("NUEVA CUENTA: ");
                        System.out.println("Crear nueva cuenta: " + separado[1]);
                        String nombre = separado[1];
                        nombre = nombre.replace(" ", "");
                        Cuenta cuentaPersonal = (Cuenta) inputObject.readObject(); //cuenta pasada por el socket

                        if(mapaCuentas.containsKey(nombre)){

                            mapaCuentas.get(nombre).add(cuentaPersonal); 

                        }else{

                            Set<Cuenta> listado = new HashSet<>();
                            listado.add(cuentaPersonal);
                            mapaCuentas.put(nombre, listado);

                        }

                        System.out.println(mapaCuentas.toString());
                        System.out.println(mapaCuentas.size());

                        
                    }else if(entrada.startsWith("CUENTAS: ")){

                        separado = entrada.split("CUENTAS:");
                        String nombre = separado[1];
                        nombre = nombre.replace(" ", "");
                        System.out.println(nombre);

                        if(mapaCuentas.containsKey(nombre) == true){
                            
                            Set<Cuenta> listado = mapaCuentas.get(nombre);
                            String lista = listado.toString();
                            out.println(lista);
                            System.out.println("Cuentas envidadas");

                        }else{

                            out.println("No existe la cuenta");

                        }

                    }else if(entrada.startsWith("ELIMINAR CUENTA: ")){

                        separado=entrada.split("ELIMINAR CUENTA:");
                        String nombre = separado[1];
                        System.out.println("Borrar cuenta: " + nombre);
                        nombre = nombre.replace(" ", "");

                        mapaCuentas.remove(nombre);
                        out.println("Eliminado correctamente");
                    

                    }else if(entrada.startsWith("tamano")){

                        System.out.println(mapaCuentas.size());

                    }else{
                        System.out.println("COMANDO ERRONEO.");
                    }
                } catch (Exception e) {
                    
                    System.out.println("Excepcion con una conexion con un cliente");
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
