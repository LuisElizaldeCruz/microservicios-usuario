package org.luis.springcloud.msvc.usuarios.msvc_usuarios.repositories;

import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
