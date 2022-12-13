import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class SocketClientUDPEj2 {
    
    public static final int PUERTO = 6742;

    public static void main(String[] args) {

        
        
        // Crea un socket datagrama
        try(DatagramSocket socket = new DatagramSocket(); BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));){

            String teclado = "";

            teclado = stdIn.readLine();

            String[] Partes = teclado.split(" ");

            String teclado2 = Partes[2];


            // Envia una peticion a 127.0.0.1:6742
            byte[] bufferEnvio = teclado2.getBytes(StandardCharsets.UTF_8);

            DatagramPacket datagrama = new DatagramPacket(bufferEnvio, bufferEnvio.length, InetAddress.getLocalHost(), PUERTO);
            socket.send(datagrama);

            // Recibe la respuesta (hay que crear un buffer para la recepcion)
            byte[] bufferRecepcion = new byte[256];
            datagrama = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socket.receive(datagrama);


            //La muestra por pantalla y cierra el socket
            String res = new String(datagrama.getData(),0, datagrama.getLength(),StandardCharsets.UTF_8);
            System.out.println(res);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
