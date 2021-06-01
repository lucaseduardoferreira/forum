package lucasferreira.com.github.forum.repository;

import java.util.Optional;

import lucasferreira.com.github.forum.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}