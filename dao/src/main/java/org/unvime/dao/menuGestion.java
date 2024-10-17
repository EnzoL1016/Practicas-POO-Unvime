package org.unvime.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Usuario;

import org.unvime.dao.service.TareaService;
import org.unvime.dao.service.UsuarioService;
import org.unvime.dao.service.AuthService;

import java.util.Scanner;

@Service
public class menuGestion {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private AuthService authService;


   static Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        Usuario usuario = null;

        while (usuario == null) {

            System.out.println("Bienvenido a la Gestión de Tareas");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    usuario = autenticar();
                    break;
                case 2:
                    usuario = registrar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    private Usuario autenticar() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario user = authService.login(usuario, contraseña);
        if (user != null) {
            System.out.println("Inicio de sesión exitoso.");
            mostrarMenuTareas(user);
            return user;
        } else {
            System.out.println("Credenciales incorrectas.");
            return null;
        }
    }
    private Usuario registrar() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario nuevoUsuario = authService.registrar(nombre, apellido, usuario, contraseña);

        if (nuevoUsuario != null) {
            System.out.println("Registro exitoso. Por favor, inicie sesión.");
            return autenticar();  // Iniciar sesión después de registrarse
        } else {
            System.out.println("El usuario ya existe. Intente nuevamente.");
            return null;
        }
    }

    private void mostrarMenuTareas(Usuario usuario) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Ver tareas");
            System.out.println("2. Crear tarea");
            System.out.println("3. Actualizar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    tareaService.mostrarTareas(usuario.getId().longValue());
                    break;
                case 2:
                    tareaService.crearTarea(usuario);
                    break;
                case 3:
                    tareaService.actualizarTarea(usuario);
                    break;
                case 4:
                    tareaService.eliminarTarea(usuario);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
