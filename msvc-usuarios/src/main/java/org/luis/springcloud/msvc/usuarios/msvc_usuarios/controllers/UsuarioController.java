package org.luis.springcloud.msvc.usuarios.msvc_usuarios.controllers;

import jakarta.validation.Valid;
import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.luis.springcloud.msvc.usuarios.msvc_usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body(usuarioOptional.get());//devuelve el objeto0
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)// 201 recurso creado
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (service.porEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("mensaje", "ya existe un usuario con ese correo electronico"));
        }

        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Usuario> o = service.porId(id);
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();
            if (!usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && service.porEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("mensaje", "ya existe un usuario con ese correo electronico"));
            }


            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build(); //si el usuario existe y se elimina noContent es un 204
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) { //metdo para validar el error y devolver mensaje
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
