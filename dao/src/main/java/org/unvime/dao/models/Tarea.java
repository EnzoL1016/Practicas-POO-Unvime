package org.unvime.dao.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public enum EstadoTarea {
        Nuevo, Pendiente, Finalizado
    }
}
