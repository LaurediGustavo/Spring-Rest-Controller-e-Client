package com.example.springrestcontrollereclient.controller;

import com.example.springrestcontrollereclient.data.ManipularArquivos;
import com.example.springrestcontrollereclient.movie.DetalhesBusca;
import com.example.springrestcontrollereclient.movie.MovieRepository;
import com.example.springrestcontrollereclient.movie.ResultadoBusca;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRestController {
    private final MovieRepository repository;

    public MovieRestController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/search")
    public ResultadoBusca buscar(@RequestParam String title) {
        ResultadoBusca buscaResult = new ResultadoBusca();
        ManipularArquivos arq = new ManipularArquivos();

        if (arq.getSizeList() > 0) {
            buscaResult = arq.buscar(title);
        }

        if (buscaResult.getResultList() == null) {
            buscaResult = this.repository.buscar(title);
            arq.addMovies(buscaResult);
        }

        return buscaResult;
    }

    @GetMapping("/movies/{id}")
    public DetalhesBusca detalhes(@PathVariable String id) {
        return this.repository.detalhes(id);
    }
}
