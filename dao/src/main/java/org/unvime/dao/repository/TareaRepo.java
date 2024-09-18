package org.unvime.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unvime.dao.models.Tarea;

import java.util.List;

public interface TareaRepo extends JpaRepository<Tarea, Long> {
    List<Tarea> findByUsuarioId(Long idUsuario);
}
