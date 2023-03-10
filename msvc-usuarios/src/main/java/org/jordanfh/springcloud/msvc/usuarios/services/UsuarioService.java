package org.jordanfh.springcloud.msvc.usuarios.services;

import org.jordanfh.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();

    Optional<Usuario> buscarId(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

    List<Usuario> listaUsuariosPorId(Iterable<Long> ids);

    // No existe en el CRUD Repository
    Optional<Usuario> buscarEmail(String email);
}
