package utils;
import clases.Usuario;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class UsuarioUtils {
    static Scanner leer = new Scanner(System.in);
    private List<Usuario> usuarios;
    public UsuarioUtils() {
        this.usuarios = new ArrayList<Usuario>();
    }
    public void registrarUsuario(String nombre, String clave) {
        if (existeUsuario(nombre)) {
            System.out.println("El usuario ya existe.");
            return;
        }
        Usuario nuevoUsuario = new Usuario(nombre, clave);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    public boolean iniciarSesion(String nombre) {

        if (!existeUsuario(nombre)) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

        System.out.print("Ingrese contraseña: ");
        String clave = leer.nextLine();


        if (verifClave(nombre, clave)) {
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Contraseña incorrecta.");
            return false;
        }
    }

    public boolean existeUsuario(String nombreUsuario) {
        return usuarios.stream().anyMatch(usuario -> usuario.getNombre().equalsIgnoreCase(nombreUsuario));
    }
    public boolean verifClave(String nombreUsuario, String clave) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombreUsuario))
                .anyMatch(usuario -> usuario.getClave().equals(clave));
    }
}
