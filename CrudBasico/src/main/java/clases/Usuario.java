package clases;
import lombok.Data;

@Data
public class Usuario {
    private String nombre;
    private String clave;

    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public Usuario() {

    }
}
