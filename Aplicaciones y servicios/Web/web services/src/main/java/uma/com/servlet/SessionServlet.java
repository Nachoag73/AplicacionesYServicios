package uma.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
      response.setContentType("text/html");
      
		int clickCount;

		//Obtiene la session o el numero de veces que se ha hecho click
		HttpSession session = request.getSession();

		if(session.getAttribute("count") != null){
			clickCount = (int) session.getAttribute("count");
		}
		else{
			clickCount = 0;
		}

		clickCount++;

		// actualiza los clicks
		session.setAttribute("count", clickCount);

		//output the clickCount for this user
		response.getOutputStream().println("<h1>Has hecho click" + clickCount + " veces.</h1>");	
  }
}
