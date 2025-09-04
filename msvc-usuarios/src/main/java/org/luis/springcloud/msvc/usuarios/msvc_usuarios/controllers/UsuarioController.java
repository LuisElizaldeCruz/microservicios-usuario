package org.luis.springcloud.msvc.usuarios.msvc_usuarios.controllers;

import jakarta.validation.Valid;
import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.luis.springcloud.msvc.usuarios.msvc_usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
>>>>>>> origin/main

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
<<<<<<< HEAD
    public List<Usuario> listar() {
=======
    public List<Usuario> listar(){
>>>>>>> origin/main
        return service.listar();
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
=======
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()) {
>>>>>>> origin/main
            return ResponseEntity.ok().body(usuarioOptional.get());//devuelve el objeto0
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }
<<<<<<< HEAD

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)// 201 recurso creado
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (service.porEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("mensaje", "ya existe un usuario con ese correo electronico"));
        }

        if (result.hasErrors()) {
            return validar(result);
=======
    @PostMapping
   // @ResponseStatus(HttpStatus.CREATED)// 201 recurso creado
    public ResponseEntity<?> crear (@Valid @RequestBody Usuario usuario, BindingResult result) {
        if(result.hasErrors()){
           return validar(result);
>>>>>>> origin/main
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> edit(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
=======
    public ResponseEntity<?> edit(@Valid @RequestBody Usuario usuario,BindingResult result , @PathVariable Long id){
        if(result.hasErrors()){
>>>>>>> origin/main
            return validar(result);
        }

        Optional<Usuario> o = service.porId(id);
<<<<<<< HEAD
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();
            if (!usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && service.porEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("mensaje", "ya existe un usuario con ese correo electronico"));
            }


=======
        if(o.isPresent()) {
            Usuario usuarioDb = o.get();
>>>>>>> origin/main
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = service.porId(id);
        if (o.isPresent()) {
=======
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()) {
>>>>>>> origin/main
            service.eliminar(id);
            return ResponseEntity.noContent().build(); //si el usuario existe y se elimina noContent es un 204
        }
        return ResponseEntity.notFound().build(); //si el usuario no existe
    }

<<<<<<< HEAD
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) { //metdo para validar el error y devolver mensaje
=======
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
>>>>>>> origin/main
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
