package com.example.springrestcontrollereclient.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "movieRepository", url = "${omdb.url}")
public interface MovieRepository {
    @GetMapping
    ResultadoBusca buscar(@RequestParam("s") String movieTitle);

    @GetMapping
    ResultadoBusca detalhes(@PathVariable("i") String id);
}
