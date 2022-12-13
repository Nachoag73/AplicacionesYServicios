public class Restaurante {
    
    public Restaurante(String CIF, int year, String pedidos) {
        this.CIF = CIF;
        this.year = year;
        this.pedidos = pedidos;
    }

    private String CIF;
    private int year;
    private String pedidos;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String cIF) {
        CIF = cIF;
    }


    public boolean equals(Object obj){

        return (obj instanceof Restaurante) && ((Restaurante) obj).CIF == this.CIF;

    }

    public int compareTo( Restaurante r){

        int resultado = CIF.compareTo(r.CIF);

        if(resultado == 0){

            resultado = Integer.compare(year, r.year);

        }

        return resultado;

    }


}
