import java.io.*;
import java.util.*;

public class Texto {
 
    public static void main (String [] args){

        List<String> listapalabras = new ArrayList<String>();

        try(BufferedReader entrada = new BufferedReader(new FileReader("Entrada.txt"));
        PrintWriter salidaa = new PrintWriter(new BufferedWriter(new FileWriter("salida.txt")));){

            
            String s;
            String salida = "";
            String [] palabras;
            boolean encontrado = false;
            int i = 0;   
            double contpal = 0;  
            double contpalcomp = 0;          

            s = entrada.readLine();
            
            while(s != null){
                System.out.println((s));
                palabras = s.split(" ");
                for (String s1 : palabras) {
                    encontrado = false;
                    i = 0;
                    contpal = contpal + s1.length();
                    
                    if(listapalabras.contains(s1) == true){

                        //System.out.println(( "palabra " + s1 + " esta"));

                        while(!encontrado && i < listapalabras.size()){
                            if(listapalabras.get(i).equals(s1)){
                                salida = salida +" " + i;
                                encontrado = true;
                            }
                            i++;
                        }
                    }else{

                        contpalcomp = contpalcomp + s1.length();
                        //System.out.println(( "palabra " + s1 + " no esta"));
                        listapalabras.add(s1);
                        salida = salida +" "+ s1;

                    }

                }
                s = entrada.readLine();
                System.out.println(salida);
                
                

                
            };
        
            salidaa.write(salida);
            salidaa.flush();
            System.out.println(contpal);
            System.out.println(contpalcomp);
            System.out.println(((contpal-contpalcomp)/contpal)*100);
            salidaa.write("\n"+"El porcentaje de compactacion es: " + ((contpal-contpalcomp)/contpal)*100);
            salidaa.flush();
        

        }catch (Exception e){
            e.printStackTrace();
        }
        
        
    

    }
}