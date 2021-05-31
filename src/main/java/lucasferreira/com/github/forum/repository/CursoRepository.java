package lucasferreira.com.github.forum.repository;

import lucasferreira.com.github.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Curso findByNome(String nomeCurso);
}
