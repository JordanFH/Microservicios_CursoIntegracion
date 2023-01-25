package org.jordanfh.springcloud.msvc.usuarios.repositories;

import org.jordanfh.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    // No existe en el CRUD Repository
    Optional<Usuario> findByEmail(String email);
}
