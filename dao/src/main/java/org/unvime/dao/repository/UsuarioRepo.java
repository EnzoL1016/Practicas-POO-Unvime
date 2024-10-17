package org.unvime.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unvime.dao.models.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
}