package uma.com.servlet;

import jakarta.servlet.http.HttpServlet;

public class usuario extends HttpServlet {
    public usuario(String contraseña, boolean b) {
        this.contraseña = contraseña;
        this.conectado = false;
    }

    
    private String contraseña;
    private boolean conectado;
   
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public boolean getConectado() {
        return conectado;
    }
    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    
}
