package org.jordanfh.springcloud.msvc.cursos.services;

import org.jordanfh.springcloud.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();

    Optional<Curso> buscarId(Long id);

    Curso guardar(Curso curso);

    void eliminar(Long id);

    Optional<Curso> buscarNombre(String nombre);
}
