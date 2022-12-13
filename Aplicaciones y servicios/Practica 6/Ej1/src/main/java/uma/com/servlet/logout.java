@WebServlet("/logout")
public class logout extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        Map<String,Usuario> mapa=(Map) session.getAttribute("mapa");


        for(String s: mapa.keySet()){

            if(mapa.get(s).getConectado() == true){

                mapa.get(s).setConectado(false);

            }else{

                try(PrintWriter writer = response.getWriter()){
                    writer.printf("<html><body> Error </body></html>");
                }

            }
        }

        
        
    } 
}