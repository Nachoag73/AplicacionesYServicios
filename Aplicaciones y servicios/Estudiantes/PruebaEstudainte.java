public class PruebaEstudainte {

    public static void main (String [] args){

        Estudiante estudiante1 = new Estudiante("2234H", "Antonio", 8);
        Estudiante estudiante2 = new Estudiante("2234D", "Arturo", 7);
        int resultado = estudiante1.compareTo(estudiante2);

        if(resultado == 0){

            System.out.println("son iguales");

        }else if(resultado >= 1){

            System.out.println("estudiante 1 es mayor que estudiante 2");

        }else if(resultado <= -1){

            System.out.println("estudiante 1 es menor que estudiante 2");
            
        }

    }

}
