package lucasferreira.com.github.forum.repository;

import lucasferreira.com.github.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {

    /*@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")(String nomeCurso);
    Equivale ao mesmo metodo abaixo

    */

    Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);
    //Como não tem nomecurso dentro de topicos, mas
    // temos o curso, então só pepgar curso nome

}
