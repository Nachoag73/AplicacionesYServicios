package uma.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CurrentDateTime", urlPatterns = {"/datetime", "/time"})
public class TimeServlet extends HttpServlet {
    Date currDateAndTime = new Date();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        // Esto se utiliza porque los Servlets son multihebra y para que se actualice de forma segura
        synchronized(currDateAndTime){
            currDateAndTime = new Date();
        }
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
          out.printf("<html><head><title>Servlet Current Date And Time</title></head><body><h1>Servlet CurrentDateAndTime at %s</h1><br/> The current date and time is: %s</body></html>", 
          request.getContextPath(), currDateAndTime);
       }
    }
  }

  