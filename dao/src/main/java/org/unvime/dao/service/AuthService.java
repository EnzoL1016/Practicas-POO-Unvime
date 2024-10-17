package org.unvime.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Usuario;
import org.unvime.dao.repository.UsuarioRepo;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepo usuarioRepo;


    public Usuario login(String usuario, String contraseña) {
        Usuario user = usuarioRepo.findByUsuario(usuario);
        if (user != null && user.getClave().equals(contraseña)) {
            return user;
        }
        return null;
    }


    public Usuario registrar(String nombre, String apellido, String usuario, String contraseña) {

        if (usuarioRepo.findByUsuario(usuario) != null) {
            return null;
        }

        // Crear nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setClave(contraseña);

        return usuarioRepo.save(nuevoUsuario);
    }
}
