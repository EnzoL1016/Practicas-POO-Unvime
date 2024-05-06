public class Vertice {
    private String nombre;
    private int numero;

    public Vertice(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public Vertice() {
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}