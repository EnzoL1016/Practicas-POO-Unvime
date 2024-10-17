package org.unvime.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Usuario;
import org.unvime.dao.repository.UsuarioRepo;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }


    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setUsuario(usuarioDetails.getUsuario());
            usuario.setClave(usuarioDetails.getClave());
            return usuarioRepo.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
}
