package uma.com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pedidos")
public class pedidos extends HttpServlet {

    List<String> LPedidos = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");
        
        try(PrintWriter writer = response.getWriter()){
            for (String s : LPedidos) {
                writer.println(s);
            }
           
        }

    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");
        
        String Pedido;

        try(BufferedReader read = request.getReader()){
           Pedido = read.readLine();
           LPedidos.add(Pedido);
        }

    } 

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");

        String PedidoNuevo;
        String PedidoAntiguo = request.getParameter("pedido");
        try(BufferedReader read = request.getReader()){
            PedidoNuevo = read.readLine();
            for (String s : LPedidos) {
                if(s.equalsIgnoreCase(PedidoAntiguo)){
                    LPedidos.remove(PedidoAntiguo);
                    LPedidos.add(PedidoNuevo);
                }
            }
            
        }

    } 

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");
        
        String Pedido;
        try(BufferedReader read = request.getReader()){
            Pedido = read.readLine();
            for (String s : LPedidos) {
                if(s.equalsIgnoreCase(Pedido)){
                    LPedidos.remove(Pedido);
                }
            }
            
        }

    } 
}
