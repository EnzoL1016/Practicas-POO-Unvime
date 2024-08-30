package utils;
import clases.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TareasUtils {
    static Scanner leer = new Scanner(System.in);
    private List<Tarea> tareas;
    public TareasUtils() {
        this.tareas = new ArrayList<Tarea>();
    }

    public boolean validarEstado(int estado){
        return estado > 0 && estado <= 3;
    }

    public void pedirDatos(String usuario){
        String titulo,descripcion;
        System.out.println("\nIngresa el ID de la tarea: ");
        int id = leer.nextInt();
        leer.nextLine();
        System.out.println("\nIngresa el titulo: ");
        titulo = leer.nextLine();
        System.out.println("\nIngresa el descripcion: ");
        descripcion = leer.nextLine();
        Tarea.Estado estado = null;
        while (estado == null) {
            System.out.println("\nIngresa el estado:\n1) Nuevo\n2) Pendiente\n3) Finalizado");
            int estadoOpc = leer.nextInt();
            leer.nextLine();

            if (validarEstado(estadoOpc)) {
                estado = Tarea.Estado.values()[estadoOpc - 1];
            } else {
                System.out.println("\nEstado invalido.\n");
            }
        }
        actualizarTarea(id,titulo,descripcion,estado, usuario);
    }

    public void crearTarea(String usuario) {
        Tarea.Estado estado = null;
        System.out.println("\nIngresa el titulo: ");
        String titulo = leer.nextLine();
        System.out.println("\nIngresa el descripcion: ");
        String descripcion = leer.nextLine();

        while (estado == null) {
            System.out.println("\nIngresa el estado:\n1) Nuevo\n2) Pendiente\n3) Finalizado");
            int estadoOpc = leer.nextInt();
            leer.nextLine();

            if (validarEstado(estadoOpc)) {
                estado = Tarea.Estado.values()[estadoOpc - 1];
            } else {
                System.out.println("\nEstado invalido.\n");
            }
        }
        Tarea nuevaTarea  = new Tarea(titulo, descripcion, estado, usuario);
        tareas.add(nuevaTarea);
        System.out.println("Tarea creada exitosamente. ID: "+nuevaTarea.getId());
    }

    public void verTareas(String usuario) {
        List<Tarea> tareasUsuario = tareas.stream()
                .filter(tarea -> tarea.getUsuario().equals(usuario))
                .toList();
        if (tareasUsuario.isEmpty()) {
            System.out.println("\nNo tienes tareas asignadas.");
        } else {
            System.out.println("\nTareas de " + usuario + ":");
            for (Tarea tarea : tareasUsuario) {
                System.out.println("\nID: " + tarea.getId() +
                        "\nTítulo: " + tarea.getTitulo() +
                        "\nDescripción: " + tarea.getDescripcion() +
                        "\nEstado: " + tarea.getEstadoIni());
            }
        }
    }


    public void actualizarTarea(int id, String nuevoTitulo, String nuevaDescripcion, Tarea.Estado nuevoEstado, String usuario) {
        Optional<Tarea> tareaOpc = tareas.stream()
                .filter(tarea -> tarea.getId() == id && tarea.getUsuario().equals(usuario))
                .findFirst();

        tareaOpc.ifPresentOrElse(tarea -> {
            if (nuevoTitulo != null && !nuevoTitulo.trim().isEmpty()) {
                tarea.setTitulo(nuevoTitulo);
            }
            if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
                tarea.setDescripcion(nuevaDescripcion);
            }
            if (nuevoEstado != null) {
                tarea.setEstadoIni(nuevoEstado);
            }
            System.out.println("Tarea actualizada exitosamente.");
        }, () -> {
            System.out.println("Tarea con ID " + id + " no encontrada o no pertenece al usuario.");
        });
    }

    public boolean eliminarTarea(int id, String usuario) {
        return tareas.removeIf(tarea -> tarea.getId() == id && tarea.getUsuario().equalsIgnoreCase(usuario));
    }
}
