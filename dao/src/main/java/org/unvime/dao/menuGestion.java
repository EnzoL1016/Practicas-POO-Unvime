package org.unvime.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Usuario;
import org.unvime.dao.models.Tarea;
import org.unvime.dao.service.TareaService;
import org.unvime.dao.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

@Service
public class menuGestion {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TareaService tareaService;

   static Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            imprimirMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    crearUsuario();
                    break;
                case 3:
                    listarTareasPorUsuario();
                    break;
                case 4:
                    crearTarea();
                    break;
                case 5:
                    actualizarTarea();
                    break;
                case 6:
                    eliminarTarea();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

        } while (opcion != 7);
    }

    private void imprimirMenu() {
        System.out.println("\nGestion de Tareas");
        System.out.println("1) Listar Usuarios");
        System.out.println("2) Crear Usuario");
        System.out.println("3) Listar Tareas por Usuario");
        System.out.println("4) Crear Tarea");
        System.out.println("5) Actualizar Tarea");
        System.out.println("6) Eliminar Tarea");
        System.out.println("0) Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        System.out.println("\nUsuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getId() + ". " + usuario.getNombre() + " " + usuario.getApellido());
        }
    }

    private void crearUsuario() {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el nombre de usuario: ");
        String usuarioNombre = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String clave = scanner.nextLine();

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setUsuario(usuarioNombre);
        usuario.setClave(clave);

        usuarioService.createUsuario(usuario);
        System.out.println("Usuario creado exitosamente.");
    }

    private void listarTareasPorUsuario() {
        System.out.print("Ingrese el ID del usuario: ");
        Long idUsuario = Long.parseLong(scanner.nextLine());

        List<Tarea> tareas = tareaService.getTareasByUsuarioId(idUsuario);
        System.out.println("\nTareas del Usuario:");
        for (Tarea tarea : tareas) {
            System.out.println(tarea.getId() + ". " + tarea.getTitulo() + " - " + tarea.getEstado());
        }
    }

    private void crearTarea() {
        System.out.print("Ingrese el título de la tarea: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el estado (Nuevo, Pendiente, Finalizado): ");
        Tarea.EstadoTarea estado = Tarea.EstadoTarea.valueOf(scanner.nextLine());

        System.out.print("Ingrese el ID del usuario: ");
        Long idUsuario = Long.parseLong(scanner.nextLine());

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setEstado(estado);
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        tarea.setUsuario(usuario);

        tareaService.createTarea(tarea);
        System.out.println("Tarea creada exitosamente.");
    }

    private void actualizarTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        Long idTarea = Long.parseLong(scanner.nextLine());

        Tarea tarea = tareaService.getTareaById(idTarea);
        if (tarea != null) {
            System.out.print("Nuevo título: ");
            tarea.setTitulo(scanner.nextLine());
            System.out.print("Nueva descripción: ");
            tarea.setDescripcion(scanner.nextLine());
            System.out.print("Nuevo estado (Nuevo, Pendiente, Finalizado): ");
            tarea.setEstado(Tarea.EstadoTarea.valueOf(scanner.nextLine()));

            tareaService.updateTarea(idTarea, tarea);
            System.out.println("Tarea actualizada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    private void eliminarTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        Long idTarea = Long.parseLong(scanner.nextLine());

        tareaService.deleteTarea(idTarea);
        System.out.println("Tarea eliminada exitosamente.");
    }
}
