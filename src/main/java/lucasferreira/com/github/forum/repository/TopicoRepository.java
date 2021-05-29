package lucasferreira.com.github.forum.repository;

import lucasferreira.com.github.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository  extends JpaRepository<Topico, Integer> {
}
