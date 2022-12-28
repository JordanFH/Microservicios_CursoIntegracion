package org.jordanfh.springcloud.msvc.usuarios.services;

import org.jordanfh.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();

    Optional<Usuario> buscarId(long id);

    Usuario guardar(Usuario usuario);

    void eliminar(long id);
}
