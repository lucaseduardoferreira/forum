package lucasferreira.com.github.forum.controller;


import lucasferreira.com.github.forum.controller.dto.DetalhesDoTopicoDto;
import lucasferreira.com.github.forum.controller.dto.TopicoDto;
import lucasferreira.com.github.forum.controller.form.AtualizacaoTopicoForm;
import lucasferreira.com.github.forum.controller.form.TopicoForm;
import lucasferreira.com.github.forum.modelo.Topico;
import lucasferreira.com.github.forum.repository.CursoRepository;
import lucasferreira.com.github.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    //localhost:8080/topicos?page=0&size=3&sort=id,desc -> exemplo da url, para passar
    //mais paramentos localhost:8080/topicos?page=0&size=3&sort=id,desc&sort=datacriacao,desc
    @GetMapping
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
    //na assinatura acima, exemplo de deixar a ordenacao default, se passar algum parametro ele nao usa pagebleDefault
    // se nao passar pagina e qtd no pageableDefault da pra definir

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicoDto cadatrar(@RequestBody @Valid TopicoForm form){
        Topico topico = form.converter(cursoRepository);
        System.out.println("teste");

        topicoRepository.save(topico);
        return new TopicoDto(topico);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }





}



