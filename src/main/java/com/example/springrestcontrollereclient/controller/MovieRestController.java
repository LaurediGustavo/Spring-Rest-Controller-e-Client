package com.example.springrestcontrollereclient.controller;

import com.example.springrestcontrollereclient.data.ManipularArquivos;
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

    @GetMapping("/movies")
    public ResultadoBusca buscar(@RequestParam String movieTitle) {
        ResultadoBusca buscaResult = new ResultadoBusca();
        ManipularArquivos arq = new ManipularArquivos();

        if (arq.getSizeList() > 0) {
            buscaResult = arq.buscar(movieTitle);
        }

        if (buscaResult == null) {
            buscaResult = this.repository.buscar(movieTitle);
            arq.addMovies(buscaResult);
        }

        return buscaResult;
    }

    @GetMapping("/movies/ID")
    public ResultadoBusca detalhes(@RequestParam String imdbId) {
        return this.repository.detalhes(imdbId);
    }
}
