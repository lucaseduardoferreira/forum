package lucasferreira.com.github.forum.controller;


import lucasferreira.com.github.forum.controller.dto.DetalhesDoTopicoDto;
import lucasferreira.com.github.forum.controller.dto.TopicoDto;
import lucasferreira.com.github.forum.controller.form.AtualizacaoTopicoForm;
import lucasferreira.com.github.forum.controller.form.TopicoForm;
import lucasferreira.com.github.forum.modelo.Topico;
import lucasferreira.com.github.forum.repository.CursoRepository;
import lucasferreira.com.github.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
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
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        return new DetalhesDoTopicoDto(topico.get());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public TopicoDto atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Topico topico = form.atualizar(id, topicoRepository);//Ja atualiza em memoria e depois atualiza no BD
        return new TopicoDto(topico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remover(@PathVariable Long id){
        topicoRepository.deleteById(id);
    }
/*
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
*/



}



