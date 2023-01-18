package org.jordanfh.springcloud.msvc.cursos.services;

import org.jordanfh.springcloud.msvc.cursos.models.UsuarioModel;
import org.jordanfh.springcloud.msvc.cursos.models.entity.Curso;
import org.jordanfh.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> buscarId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Curso> buscarNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    // Implementación de los métodos remotos
    @Override
    public Optional<UsuarioModel> asignarUsuario(UsuarioModel usuario, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioModel> crearUsuario(UsuarioModel usuario, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioModel> eliminarUsuario(UsuarioModel usuario, Long id) {
        return Optional.empty();
    }
}
