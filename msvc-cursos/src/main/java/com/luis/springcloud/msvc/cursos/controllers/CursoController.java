package com.luis.springcloud.msvc.cursos.controllers;

import com.luis.springcloud.msvc.cursos.entity.Curso;
import com.luis.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoService service; //se inyecta la interfaz

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(service.listar()); //devuelve una respuesta 200 y la lista de cursos
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build(); //devuelve una respuesta 404 si el curso no existe
    }

}
