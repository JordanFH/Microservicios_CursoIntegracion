package org.jordanfh.springcloud.msvc.cursos.controllers;

import jakarta.validation.Valid;
import org.jordanfh.springcloud.msvc.cursos.entity.Curso;
import org.jordanfh.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = {"/api/curso", "/api/curso/"})
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    /*public List<Curso> listar() {
        return service.listar();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Curso> optional = service.buscarId(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {

        if (result.hasErrors()) {
            return validarResultado(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, @PathVariable Long id, BindingResult result) {

        if (result.hasErrors()) {
            return validarResultado(result);
        }

        Optional<Curso> optional = service.buscarId(id);
        if (optional.isPresent()) {
            Curso dbCurso = optional.get();

            if (!curso.getNombre().equalsIgnoreCase(dbCurso.getNombre()) && service.buscarNombre(curso.getNombre()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Mensaje", "Ya existe un curso con ese nombre"));
            }

            dbCurso.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(dbCurso));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> optional = service.buscarId(id);
        if (optional.isPresent()) {
            service.eliminar(optional.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validarResultado(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo <" + error.getField() + "> " + error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

}
