package org.jordanfh.springcloud.msvc.usuarios.services;

import org.jordanfh.springcloud.msvc.usuarios.models.entity.Usuario;
import org.jordanfh.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listaUsuarios(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }

    // No existe en el CRUD Repository
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarEmail(String email) {
        return repository.findByEmail(email);
    }

}
