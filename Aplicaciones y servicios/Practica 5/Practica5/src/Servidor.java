import java.io.*;
import java.net.*;

public class Servidor {
    private static final int respuesta_125 = 125;
    private static final int respuesta_200 = 200;
    private static final int respuesta_421 = 421;
    private static final int respuesta_425 = 425;

    private static final int PUERTO = 3168;

    public static void main(String[] args) {

        int puertoTCP;
        String comandoRecibido = "";
        Boolean recibido;
        String[] comandoSeparado;
        System.out.println("SERVIDOR INICIALIZADO");
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = server.accept();
                System.out.println("PETICION ACEPTADA");
                try (
                    BufferedReader entrada_comandos = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida_comandos = new PrintWriter(cliente.getOutputStream(), true);) 
                {

                    String comando;
                    String[] comandoSeparado2;
                    comando = entrada_comandos.readLine();
                    comandoSeparado2 = comando.split(" ");
                    puertoTCP = Integer.parseInt(comandoSeparado2[1]);
                    comandoRecibido = entrada_comandos.readLine();

                    String[] comandoSeparado1 = comandoRecibido.split(" ");
                    if ((!comandoSeparado1[0].equals("LIST")) && (!comandoSeparado1[0].equals("RETR")) && (!comandoSeparado1[0].equals("STOR"))) {

                        salida_comandos.println(respuesta_421);
                        recibido = false;
                        
                    } else {

                        salida_comandos.println(respuesta_200);
                        recibido = true;
                    }
                    if (recibido) {
                        boolean entrada = false;
                        if (entrada_comandos.readLine().equals("125")) {
                            entrada = true;
                        } else {
                            entrada = false;
                        }
                        if (entrada) {
                            try (
                                Socket clienteTCP = new Socket("127.0.0.1", puertoTCP);
                                PrintWriter salida_lista = new PrintWriter(clienteTCP.getOutputStream(), true);
                                DataInputStream entrada_bytes = new DataInputStream(clienteTCP.getInputStream());
                                DataOutputStream salida_bytes = new DataOutputStream(clienteTCP.getOutputStream());) 
                                
                            {
                                comandoSeparado = comandoRecibido.split(" ");
                                switch (comandoSeparado[0]) {
                                    case "LIST":
                                        String directorio = System.getProperty("user.dir");
                                        File carpetas = new File(directorio);
                                        String[] listados = carpetas.list();
                                        salida_lista.println(listados.length);
                                        for (String s : listados) {
                                            salida_lista.println(s);
                                        }
                                        break;
                                    case "RETR":
                                        try (FileInputStream entrada_fichero = new FileInputStream(
                                                comandoSeparado[1])) {
                                            int b;

                                            salida_comandos.println(respuesta_125);
                                            while ((b = entrada_fichero.read()) != -1) {
                                                salida_bytes.write(b);
                                            }
                                        } catch (FileNotFoundException e) {
                                            System.out.println("no existe directorio");
                                            salida_comandos.println(respuesta_425);
                                        }
                                        break;
                                    case "STOR":

                                        if (!entrada_comandos.readLine().equals("125")) {
                                            System.out.println("El cliente no ha enviado el fichero");
                                        } else {
                                            try (FileOutputStream salida_fichero = new FileOutputStream(
                                                    comandoSeparado[1])) {
                                                int b;
                                                while ((b = entrada_bytes.read()) != -1) {
                                                    salida_fichero.write(b);
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
