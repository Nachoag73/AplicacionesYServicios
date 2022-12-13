public class PruebaAgenda {
    
    /**
     * @param args
     * @throws Exception
     */
    public static void main (String [] args) throws Exception{

        Restaurante r1 = new Restaurante("2234H", 54, "h");
        Restaurante r2 = new Restaurante("555J", 56, "h");
        Agenda a = new Agenda<String, String>();
        AgendaLista a2 = new AgendaLista<String, String>();
        int resultado = r1.compareTo(r2);

        //Pruebas:

        a.incluir(r1.getCIF());
        a.incluir(r2.getCIF());
        r2.setPedidos("Hierro");
        a.asociar(r2.getCIF(), r2.getPedidos());
        a.asociar(r2.getCIF(), "salchichas");
        a.asociar(r1.getCIF(), "kiwis");
        a.asociar(r1.getCIF(), "melones");
        System.out.print(a.toString());

        a.eliminar(r2.getCIF(), "Hierro");
        System.out.print(a.toString());


        if(resultado == 0){

            System.out.println("son iguales");

        }else if(resultado >= 1){

            System.out.println("Restaurante 1 es mayor que Restaurante 2");

        }else if(resultado <= -1){

            System.out.println("Restaurante 1 es menor que Restaurante 2");
            
        }


        System.out.print("Pruebas lista: \n");

        a2.incluir(r1.getCIF());
        a2.incluir(r2.getCIF());
        r2.setPedidos("Hierro");
        a2.asociar(r2.getCIF(), r2.getPedidos());
        a2.asociar(r2.getCIF(), "salchichas");
        a2.asociar(r1.getCIF(), "kiwis");
        a2.asociar(r1.getCIF(), "melones");
        System.out.print(a2.toString());

        a2.eliminar(r2.getCIF(), "Hierro");
        System.out.print(a2.toString());

    }

}
