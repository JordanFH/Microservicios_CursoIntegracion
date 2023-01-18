package org.jordanfh.springcloud.msvc.cursos.services;

import org.jordanfh.springcloud.msvc.cursos.models.UsuarioModel;
import org.jordanfh.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();

    Optional<Curso> buscarId(Long id);

    Curso guardar(Curso curso);

    void eliminar(Long id);

    Optional<Curso> buscarNombre(String nombre);

    // Métodos remotos relacionados con el HttpClient (para la comunicación de microservicios por medio de ApiREST)

    Optional<UsuarioModel> asignarUsuario(UsuarioModel usuario, Long id);
    Optional<UsuarioModel> crearUsuario(UsuarioModel usuario, Long id);
    Optional<UsuarioModel> eliminarUsuario(UsuarioModel usuario, Long id);
}
