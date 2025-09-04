package org.luis.springcloud.msvc.usuarios.msvc_usuarios.repositories;

import org.luis.springcloud.msvc.usuarios.msvc_usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); //hace una consulta
=======
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
>>>>>>> origin/main
}
