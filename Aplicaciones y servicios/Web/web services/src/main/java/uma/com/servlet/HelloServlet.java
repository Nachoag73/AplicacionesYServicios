package uma.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    static String PAGE_HEADER = "<html><head><title>Aplicaciones y Servicios</title></head>";
    static String PAGE_BODY= "<body>Hola clase! Esto es una respuesta HTTP.</body></html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
      response.setContentType("text/html");
        try(PrintWriter writer = response.getWriter()){
          writer.println(PAGE_HEADER);
          writer.println(PAGE_BODY);
        }
    }
  }