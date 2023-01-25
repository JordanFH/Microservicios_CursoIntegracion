package org.jordanfh.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.jordanfh.springcloud.msvc.usuarios.models.entity.Usuario;
import org.jordanfh.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = {"/api/usuario", "/api/usuario/"})
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    /*public List<Usuario> listar() {
        return service.listar();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> optional = service.buscarId(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (service.buscarEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Mensaje", "Ya existe un usuario con ese email"));
        }

        if (result.hasErrors()) {
            return validarResultado(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, @PathVariable Long id, BindingResult result) {

        if (result.hasErrors()) {
            return validarResultado(result);
        }

        Optional<Usuario> optional = service.buscarId(id);
        if (optional.isPresent()) {
            Usuario usuarioDB = optional.get();

            if (!usuario.getEmail().equalsIgnoreCase(usuarioDB.getEmail()) && service.buscarEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Mensaje", "Ya existe un usuario con ese email"));
            }

            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> optional = service.buscarId(id);
        if (optional.isPresent()) {
            service.eliminar(id);
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

    @GetMapping("/matriculados")
    public ResponseEntity<?> listaMatriculados(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.listaUsuariosPorId(ids));
    }

}
