import java.util.*; //Añade todas las librerias util
//elemento es el nombre restaurante k
//V son los pedidos de los restaurantes

public class AgendaLista <K, V>{
    
    public AgendaLista() {
        this.elementos = new TreeMap<K,List<V>>();
    }

    private Map<K, List<V>> elementos;


    public Map<K, List<V>> getElementos() {
        return elementos;
    }

    public void setElementos(Map<K, List<V>> elementos) {
        this.elementos = elementos;
    }

    public void incluir(K elemento){

        List<V> conjunto = new ArrayList();
        elementos.put(elemento, conjunto);

    }

    public void asociar(K elemento, V subelemento)throws Exception{

        if(!elementos.containsKey(elemento) || elementos.get(elemento).contains(subelemento)){

            throw new Exception();

        }else{

            elementos.get(elemento).add(subelemento);

        }

    }

    public boolean eliminar(K elemento, V subelemento) throws Exception{
    
        return elementos.get(elemento).remove(subelemento);

    }


    @Override
    public String toString(){

        String salida = "";

        for(K key: elementos.keySet()){ //Recorre todo el mapa

            salida =  salida +  "Elemento" +  key +  ": ";

            for(V value: elementos.get(key)){

                salida = salida +  value + ", ";   

            }

            salida = salida + "\n"; 
        }

        return salida;
    }

}