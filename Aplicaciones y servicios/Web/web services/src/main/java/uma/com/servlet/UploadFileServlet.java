package uma.com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upload")
public class UploadFileServlet extends HttpServlet {

    final int BYTES = 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String fileToUpload = request.getParameter("filename");
        try(
        ServletInputStream inStream = request.getInputStream();
        FileOutputStream outFile = new FileOutputStream(fileToUpload))
        {
          byte[] bbuf = new byte[BYTES];
          int length;
          while ((inStream != null) && ((length = inStream.read(bbuf)) != -1))
          {
            outFile.write(bbuf,0,length);
          }
          outFile.flush();
          response.getWriter().println("Fichero guardado con exito");
        }
        catch (Exception e){
          response.getWriter().println("Error al recibir el fichero: "+e.getMessage());
          response.setStatus(500);
        }
    }
  }