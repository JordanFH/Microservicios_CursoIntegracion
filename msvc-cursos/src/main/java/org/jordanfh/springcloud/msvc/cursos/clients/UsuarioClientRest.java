package org.jordanfh.springcloud.msvc.cursos.clients;

import org.jordanfh.springcloud.msvc.cursos.models.UsuarioModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001/api/usuario")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    UsuarioModel detalle(@PathVariable Long id);

    @PostMapping
    UsuarioModel crear(@RequestBody UsuarioModel usuario);
}