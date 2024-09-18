package org.unvime.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unvime.dao.models.Tarea;
import org.unvime.dao.repository.TareaRepo;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepo tareaRepo;

    public Tarea getTareaById(Long id) {
        return tareaRepo.findById(id).orElse(null);
    }
    public List<Tarea> getTareasByUsuarioId(Long idUsuario) {
        return tareaRepo.findByUsuarioId(idUsuario);
    }

    public Tarea createTarea(Tarea tarea) {
        return tareaRepo.save(tarea);
    }

    public Tarea updateTarea(Long id, Tarea tareaDetails) {
        Tarea tarea = tareaRepo.findById(id).orElse(null);
        if (tarea != null) {
            tarea.setTitulo(tareaDetails.getTitulo());
            tarea.setDescripcion(tareaDetails.getDescripcion());
            tarea.setEstado(tareaDetails.getEstado());
            return tareaRepo.save(tarea);
        }
        return null;
    }

    public void deleteTarea(Long id) {
        tareaRepo.deleteById(id);
    }
}
