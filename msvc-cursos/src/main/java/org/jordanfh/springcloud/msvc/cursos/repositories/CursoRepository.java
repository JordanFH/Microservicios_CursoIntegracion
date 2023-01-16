package org.jordanfh.springcloud.msvc.cursos.repositories;

import org.jordanfh.springcloud.msvc.cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}
