package clases;
import lombok.Data;

@Data
public class Tarea {
    private static int contador = 0;
    private int id;
    private String titulo;
    private String descripcion;
    private Estado estadoIni;
    private String usuario;

    public Tarea(String titulo, String descripcion, Estado estadoIni,String usuario) {
        this.id = ++contador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoIni = estadoIni;
        this.usuario = usuario;
    }

    public enum Estado{
        NUEVO,
        PENDIENTE,
        FINALIZADO
    }

    @Override
    public String toString() {
        return "Tarea:\n" + "Titulo:" + titulo + "\nDescripcion:" + descripcion+ "\nEstado:" + estadoIni;
    }
}
