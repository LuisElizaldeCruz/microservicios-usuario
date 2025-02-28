package org.luis.springcloud.msvc.usuarios.msvc_usuarios.controllers;

import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.luis.springcloud.msvc.usuarios.msvc_usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body(usuarioOptional.get());//devuelve el objeto0
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }
    @PostMapping
   // @ResponseStatus(HttpStatus.CREATED)// 201 recurso creado
    public ResponseEntity<?> crear (@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o = service.porId(id);

        if(o.isPresent()) {
            Usuario usuarioDb = o.get();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build(); //si el usuario existe y se elimina noContent es un 204
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }
}
