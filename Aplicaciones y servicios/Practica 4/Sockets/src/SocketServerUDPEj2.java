import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


public class SocketServerUDPEj2 {

    public static final int PUERTO = 6742;

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            byte[] bufferRecepcion = new byte[256];

            while (true) {
                // Recibe una peticion
                DatagramPacket datagrama = new DatagramPacket(bufferRecepcion,bufferRecepcion.length);
                socket.receive(datagrama);
                

                // Lo procesa (muestra por pantalla)
                String res = new String (datagrama.getData(),0, datagrama.getLength(), StandardCharsets.UTF_8);
                System.out.println(res);
                // Envia una respuesta

                ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(res));

                DateTimeFormatter formato_fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                String fecha = zonedDateTime.format(formato_fecha);


                byte[] bufferEnvio = fecha.getBytes(StandardCharsets.UTF_8);
                int port = datagrama.getPort();
                InetAddress addr = datagrama.getAddress();
                datagrama = new DatagramPacket(bufferEnvio, bufferEnvio.length, addr, port);
                socket.send(datagrama);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
