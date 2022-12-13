package uma.com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
    Map <String, usuario > Mmapa = new HashMap<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");

        usuario usuario1 = new usuario("hola123", false);
        Mmapa.put("Pepe", usuario1);

        String contraseña;
        String usuario = request.getParameter("usuario");
        try(BufferedReader read = request.getReader()){
            contraseña = read.readLine();
            if(Mmapa.containsKey(usuario)){
                if(Mmapa.get(usuario).getContraseña().equalsIgnoreCase(contraseña)){
                    Mmapa.get(usuario).setConectado(true);
                }else{
                    try(PrintWriter writer = response.getWriter()){
                        writer.printf("<html><body> contraseña invalida: %s </body></html>", contraseña);
                    }
                }
                
            }else{
                try(PrintWriter writer = response.getWriter()){
                    writer.printf("<html><body> Usuario no valido </body></html>");
                }
            }
           
            
        }
        
    } 
}
