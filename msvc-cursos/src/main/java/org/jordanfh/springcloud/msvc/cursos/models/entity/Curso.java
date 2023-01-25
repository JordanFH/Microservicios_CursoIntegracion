package org.jordanfh.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.jordanfh.springcloud.msvc.cursos.models.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos") // Si no lo consideramos con el parametro "name" se creara con el nombre de la clase
public class Curso {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCurso")
    private List<Matricula> matriculas;
    @Transient
    private List<UsuarioModel> usuarios;

    // Constructores

    public Curso() {
        matriculas = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    // Métodos de la lista matrículas

    public void addMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public void removeMatricula(Matricula matricula) {
        matriculas.remove(matricula);
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }
}
