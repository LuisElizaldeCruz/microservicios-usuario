package org.luis.springcloud.msvc.usuarios.msvc_usuarios.services;

import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
