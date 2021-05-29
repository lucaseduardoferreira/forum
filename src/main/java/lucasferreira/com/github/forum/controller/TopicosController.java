package lucasferreira.com.github.forum.controller;


import lucasferreira.com.github.forum.controller.dto.TopicoDto;
import lucasferreira.com.github.forum.modelo.Curso;
import lucasferreira.com.github.forum.modelo.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @GetMapping
    public List<TopicoDto> lista() {
        Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programação"));

        return TopicoDto.coverter(Arrays.asList(topico, topico, topico));
    }

}



