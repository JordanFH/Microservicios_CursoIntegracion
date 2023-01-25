package org.jordanfh.springcloud.msvc.cursos.services;

import org.jordanfh.springcloud.msvc.cursos.clients.UsuarioClientRest;
import org.jordanfh.springcloud.msvc.cursos.models.UsuarioModel;
import org.jordanfh.springcloud.msvc.cursos.models.entity.Curso;
import org.jordanfh.springcloud.msvc.cursos.models.entity.Matricula;
import org.jordanfh.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private UsuarioClientRest client;

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
    @Transactional(readOnly = true)
    public Optional<Curso> buscarNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    // Implementación de los métodos remotos
    @Override
    @Transactional
    public Optional<UsuarioModel> asignarUsuario(UsuarioModel usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if (optional.isPresent()) {
            UsuarioModel usuarioMsvc = client.detalle(usuario.getId());
            Curso curso = optional.get();
            Matricula matricula = new Matricula();
            matricula.setIdUsuario(usuarioMsvc.getId());
            curso.addMatricula(matricula);
            repository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UsuarioModel> crearUsuario(UsuarioModel usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if (optional.isPresent()) {
            UsuarioModel usuarioNewMsvc = client.crear(usuario);
            Curso curso = optional.get();
            Matricula matricula = new Matricula();
            matricula.setIdUsuario(usuarioNewMsvc.getId());
            curso.addMatricula(matricula);
            repository.save(curso);
            return Optional.of(usuarioNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UsuarioModel> eliminarUsuario(UsuarioModel usuario, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if (optional.isPresent()) {
            UsuarioModel usuarioMsvc = client.detalle(usuario.getId());
            Curso curso = optional.get();
            Matricula matricula = new Matricula();
            matricula.setIdUsuario(usuarioMsvc.getId());
            curso.removeMatricula(matricula);
            repository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> matriculadosPorCurso(Long id) {
        Optional<Curso> optional = repository.findById(id);

        if (optional.isPresent()) {
            Curso dbCurso = optional.get();

            if (!dbCurso.getMatriculas().isEmpty()) {
                List<Long> ids = dbCurso.getMatriculas().stream().map(Matricula::getIdUsuario).toList();

                List<UsuarioModel> usuarios = client.listaUsuariosPorId(ids);

                dbCurso.setUsuarios(usuarios);
            } else {
                return Optional.of(dbCurso);
            }
        }
        return Optional.empty();
    }
}
