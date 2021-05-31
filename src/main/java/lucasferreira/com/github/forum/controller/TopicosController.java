package lucasferreira.com.github.forum.controller;


import lucasferreira.com.github.forum.controller.dto.TopicoDto;
import lucasferreira.com.github.forum.controller.form.TopicoForm;
import lucasferreira.com.github.forum.modelo.Topico;
import lucasferreira.com.github.forum.repository.CursoRepository;
import lucasferreira.com.github.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TopicoDto cadatrar(@RequestBody TopicoForm form){
        Topico topico = form.converter(cursoRepository);

        topicoRepository.save(topico);
        return new TopicoDto(topico);

    }

}



