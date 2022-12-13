public class Estudiante implements Comparable<Estudiante>{

    public Estudiante(String DNI, String nombre, double nota) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.nota = nota;
    }

    private String nombre;
    private String DNI;
    private double nota;

    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getMedia() {
        return nota;
    }
    public void setMedia(double media) {
        this.nota = media;
    }

    public int compareTo(Estudiante p) {

        int resultado = DNI.compareToIgnoreCase(p.DNI);

        if (resultado == 0) {

            resultado = nombre.compareToIgnoreCase(p.nombre);

        }

        return resultado;
    }

   
}
