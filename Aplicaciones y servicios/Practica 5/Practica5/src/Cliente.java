import java.io.*;
import java.net.*;

public class Cliente {
    // respuestas
    private static final int respuesta_125 = 125;
    private static final int respuesta_425 = 425;
    private static final int PUERTO = 3168;

    public static void main(String[] args) {
        int puertoTCP = 5656;
        String comandoEnviado = "", comandoRecibido = "";
        String[] comandoSeparado;
        try (
                Socket cliente = new Socket("127.0.0.1", PUERTO);
                PrintWriter salida_comandos = new PrintWriter(cliente.getOutputStream(), true);
                BufferedReader entrada_teclado = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader entrada_comandos = new BufferedReader(new InputStreamReader(cliente.getInputStream()));) {

            salida_comandos.println("PORT " + puertoTCP);

            String comando;
            comando = entrada_teclado.readLine();
            salida_comandos.println(comando);

            comandoRecibido = entrada_comandos.readLine();
            if (comandoRecibido.equals("200")) {

                try (ServerSocket serverTCP = new ServerSocket(puertoTCP)) {
                    salida_comandos.println(respuesta_125);
                    Socket clienteTCP = serverTCP.accept();
                    try (
                            BufferedReader entrada_lista = new BufferedReader(new InputStreamReader(clienteTCP.getInputStream()));
                            DataOutputStream salida_bytes = new DataOutputStream(clienteTCP.getOutputStream());
                            DataInputStream entrada_bytes = new DataInputStream(clienteTCP.getInputStream());) {

                        comandoSeparado = comandoEnviado.split(" ");
                        switch (comandoSeparado[0]) {
                            case "LIST":
                                int numeroLineas = Integer.parseInt(entrada_lista.readLine());
                                for (int i = 0; i < numeroLineas; i++) {
                                    System.out.println(entrada_lista.readLine());
                                }
                                break;
                            case "RETR":
                                if (!entrada_comandos.readLine().equals("125")) {
                                    System.out.println("no ha enviado el fichero el server");
                                } else {
                                    try (FileOutputStream salida_fichero = new FileOutputStream(comandoSeparado[1])) {
                                        int b;
                                        while ((b = entrada_bytes.read()) != -1) {
                                            salida_fichero.write(b);
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "STOR":
                                try (FileInputStream entrada_fichero = new FileInputStream(comandoSeparado[1])) {
                                    int b;
                                    salida_comandos.println(respuesta_125);
                                    while ((b = entrada_fichero.read()) != -1) {
                                        salida_bytes.write(b);
                                    }
                                } catch (FileNotFoundException e) {
                                    System.out.println("no se encuentra el directorio");
                                    salida_comandos.println(respuesta_425);
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("comando inexistente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
