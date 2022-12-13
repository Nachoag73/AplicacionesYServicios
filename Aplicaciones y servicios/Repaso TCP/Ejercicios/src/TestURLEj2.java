import java.io.*;
import java.net.*;


public class TestURLEj2 {

    public static void main(String[] args){
        try {
            URL url = new URL("https://blockchain.info/tobtc?currency=EUR&value=1");
            
            InputStream is = url.openStream();            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

            br.close(); // Con la clase URL, lo que hay que cerrar es el stream

        } catch (MalformedURLException ex) {
            System.out.println("URL no valida");
        } catch (IOException ex) {
            System.out.println("Error en el flujo de datos: " + ex.getLocalizedMessage());
        }
    }
    
}
