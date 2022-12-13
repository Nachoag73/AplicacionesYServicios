package uma.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/name")
public class PostNameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
      response.setContentType("text/html");
      String name = request.getParameter("name");
      String surname = request.getParameter("surname");
        try(PrintWriter writer = response.getWriter()){
          writer.printf("<html><body> Tu nombre es %s y tu apellido %s </body></html>", name, surname);
        }
    } 
  }