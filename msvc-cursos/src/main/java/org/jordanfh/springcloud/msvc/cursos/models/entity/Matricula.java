package org.jordanfh.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idUsuario;

    /*@DecimalMin(value = "0.0")
    @DecimalMax(value = "20.0")
    private Float nota;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Matricula oMatricula)) {
            return false;
        }

        return this.idUsuario != null && this.idUsuario.equals(oMatricula.getIdUsuario());
    }
}
