package org.unvime.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Tarea;
import org.unvime.dao.models.Usuario;
import org.unvime.dao.repository.TareaRepo;

import java.util.List;
import java.util.Scanner;

@Service
public class TareaService {

    @Autowired
    private TareaRepo tareaRepo;
    static Scanner leer = new Scanner(System.in);

    public void mostrarTareas(Long id) {
        List<Tarea> tareas = tareaRepo.findByUsuarioId(id);

        if (tareas.isEmpty()) {
            System.out.println("No tienes tareas.");
        } else {
            tareas.forEach(tarea ->
                    System.out.printf("\nID: %d\nTítulo: %s\nDescripcion: %s\nEstado: %s%n\n", tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getEstado())
            );
        }
    }


    public void crearTarea(Usuario usuarioActual) {
        System.out.println("Título de la tarea:");
        String titulo = leer.nextLine();
        System.out.println("Descripción de la tarea:");
        String descripcion = leer.nextLine();


        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setTitulo(titulo);
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setEstado(obtenerEstadoTarea());
        nuevaTarea.setUsuario(usuarioActual);

        tareaRepo.save(nuevaTarea);

        System.out.println("Tarea creada con éxito.");
    }



    public void actualizarTarea(Usuario usuarioActual) {
        System.out.println("Ingrese el ID de la tarea a actualizar:");

        String input = leer.nextLine();

        // Validar que el input no esté vacío y sea un número válido
        if (input.isEmpty()) {
            System.out.println("No se ingresó ningún ID.");
            return;
        }

        try {
            long idTarea = Long.parseLong(input);
            Tarea tarea = tareaRepo.findByIdAndUsuarioId(idTarea, usuarioActual.getId().longValue());

            if (tarea == null) {
                System.out.println("No se encontró una tarea con ese ID.");
                return;
            }

            System.out.println("Nuevo título (Enter para mantener actual):");
            String nuevoTitulo = leer.nextLine();
            if (!nuevoTitulo.isEmpty()) tarea.setTitulo(nuevoTitulo);

            System.out.println("Nueva descripción (Enter para mantener actual):");
            String nuevaDescripcion = leer.nextLine();
            if (!nuevaDescripcion.isEmpty()) tarea.setDescripcion(nuevaDescripcion);

            System.out.println("Nuevo estado (1 - Nuevo, 2 - Pendiente, 3 - Terminado):");
            tarea.setEstado(obtenerEstadoTarea());

            tareaRepo.save(tarea);
            System.out.println("Tarea actualizada con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("El ID ingresado no es válido. Debe ser un número.");
        }
    }




    public void eliminarTarea(Usuario usuario) {
        Tarea tarea = obtenerTareaPorIdYUsuario(usuario);
        if (tarea != null) {
            tareaRepo.delete(tarea);
            System.out.println("Tarea eliminada.");
        }
    }

    public Tarea obtenerTareaPorId(Usuario usuario) {
        System.out.print("ID de la tarea: ");
        Long idTarea = leer.nextLong();
        Tarea tarea = tareaRepo.findByIdAndUsuarioId(idTarea, usuario.getId().longValue());

        if (tarea == null) {
            System.out.println("Tarea no encontrada o no pertenece al usuario.");
        }

        return tarea;
    }


    private Tarea obtenerTareaPorIdYUsuario(Usuario usuario) {
        System.out.print("ID de la tarea: ");
        Long idTarea = leer.nextLong();
        leer.nextLine(); // Limpiar buffer

        Tarea tarea = tareaRepo.findByIdAndUsuarioId(idTarea, usuario.getId().longValue());
        if (tarea == null) {
            System.out.println("No se encontró la tarea.");
        }
        return tarea;
    }

    private Tarea.EstadoTarea obtenerEstadoTarea() {
        System.out.println("Nuevo estado\n1) Nuevo\n2) Pendiente\n3) Terminado");
        return convertirNumeroAEstado(leer.nextInt());
    }


    private Tarea.EstadoTarea convertirNumeroAEstado(int opcion) {
        switch (opcion) {
            case 1: return Tarea.EstadoTarea.Nuevo;
            case 2: return Tarea.EstadoTarea.Pendiente;
            case 3: return Tarea.EstadoTarea.Finalizado;
            default:
                System.out.println("Opción inválida. Se asignará estado 'Nuevo' por defecto.");
                return Tarea.EstadoTarea.Nuevo;
        }
    }
}
