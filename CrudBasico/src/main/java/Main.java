import clases.Usuario;
import utils.TareasUtils;
import utils.UsuarioUtils;

import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {
        UsuarioUtils userutils = new UsuarioUtils();
        TareasUtils tareasutils = new TareasUtils();
        String userActual = null;
        Usuario u = new Usuario();

        while(true) {
            System.out.println("-----Bienvenido-----");
            if(userActual == null){
                System.out.println("\n1) Registrar Usuario\n2) Iniciar Sesion\n");
            }else{
                System.out.println("""
                        3) Agregar Tarea
                        4) Ver Tareas
                        5) Actualizar Tareaa
                        6) Eliminar Tarea
                        7) Cerrar Sesion
                        """);
            }
            System.out.println("\n0) Salir\n");
            int opcion = leer.nextInt();
            leer.nextLine();
            if(opcion >= 3 && opcion <= 6 && (userActual == null)) {
                opcion = -1;
            }else if(opcion < 3 && (userActual != null)){
                opcion = -1;
            }
            switch(opcion) {
                case 1:
                    System.out.println("\nIngrese el nombre de usuario: ");
                    String nombre = leer.nextLine();
                    System.out.println("\nIngrese la clave del usuario: ");
                    String clave = leer.nextLine();
                    userutils.registrarUsuario(nombre, clave);
                    break;
                    case 2:
                        System.out.print("\nIngrese nombre de usuario: ");
                        String nombreUsuario = leer.nextLine();
                        if(userutils.iniciarSesion(nombreUsuario)){
                            userActual = nombreUsuario;
                        };
                        break;
                        case 3:
                            tareasutils.crearTarea(userActual);
                            break;
                            case 4:
                                tareasutils.verTareas(userActual);
                                break;
                                case 5:
                                    System.out.println("\nA continuacion se le pedira los nuevos datos:");
                                    tareasutils.pedirDatos(userActual);
                                    break;
                                    case 6:
                                        System.out.print("Ingrese ID de la tarea a eliminar: ");
                                        int idEliminar = leer.nextInt();
                                        leer.nextLine();
                                        if (tareasutils.eliminarTarea(idEliminar, userActual)) {
                                            System.out.println("Tarea eliminada exitosamente.");
                                        } else {
                                            System.out.println("Tarea con ID " + idEliminar + " no encontrada o no pertenece al usuario.");
                                        }
                                        break;
                                        case 7:
                                            if (userActual != null) {
                                                userActual = null;
                                                System.out.println("Sesión cerrada exitosamente.");
                                            } else {
                                                System.out.println("No hay ninguna sesion iniciada.");
                                            }
                                            break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
