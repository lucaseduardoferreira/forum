package lucasferreira.com.github.forum.controller;


import lucasferreira.com.github.forum.controller.dto.TopicoDto;
import lucasferreira.com.github.forum.modelo.Curso;
import lucasferreira.com.github.forum.modelo.Topico;
import lucasferreira.com.github.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public List<TopicoDto> lista() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.coverter(topicos);
    }

}



