package uma.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/info")
public class info extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        Map<String,Usuario> mapa=(Map) session.getAttribute("mapa");
        String salida="<html><body><h3> ";
        for(String s: mapa.keySet()){

            if(mapa.get(s).getEstado()){
                salida +="Nombre: " + s + " Esta conectado";
            }else{
                salida +="Nombre: " + s + " Esta desconectado";
            }
            salida +="\n";
        }
        salida+="</h3></body></html>";


        try(PrintWriter writer = response.getWriter()){

            writer.println(salida);


        }

    }
}