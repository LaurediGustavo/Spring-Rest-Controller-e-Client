package com.example.springrestcontrollereclient.data;

import com.example.springrestcontrollereclient.movie.Movie;
import com.example.springrestcontrollereclient.movie.ResultadoBusca;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManipularArquivos {
    private List<Movie> movies;
    private CSVWriter arquivo;

    public ManipularArquivos() {
        pesquisarArquivo();
    }

    private void pesquisarArquivo() {
        String filePath = getFilePath("cache.csv");

        try(Stream<String> linhas = Files.lines(Path.of(filePath));
            Writer writer = Files.newBufferedWriter(Path.of(filePath))) {

            criarMovie(linhas);
            arquivo = new CSVWriter(writer);
            arquivo.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void criarMovie(Stream<String> linhas) {
        Movie movie;

        for (String linha : linhas.collect(Collectors.toList())) {
            String[] split = linha.split(",(?=\\S)");

            movie = new Movie();
            movie.setImdbId(split[0]);
            movie.setTitle(split[1]);
            movie.setYear(split[2]);

            this.movies.add(movie);
        }
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getFile());

        return file.getPath();
    }

    public long getSizeList() {
        return this.movies.stream().skip(1).count();
    }

    public ResultadoBusca buscar(String movieTitle) {
        ResultadoBusca resultadoBusca = new ResultadoBusca();
        List<Movie> movieList = this.movies.stream()
                .skip(1)
                .filter(m -> m.getTitle().contains(movieTitle))
                .collect(Collectors.toList());

        if (movieList.size() > 0) {
            resultadoBusca.setResultList(movieList);
            resultadoBusca.setTotal(String.valueOf(movieList.size()));
            resultadoBusca.setResponse("true");
        }

        return resultadoBusca;
    }

    public void addMovies(ResultadoBusca buscaResult) {
        for (Movie movie : buscaResult.getResultList()) {
            Optional<Movie> movieFilter = this.movies.stream()
                    .filter(m -> m.getImdbId() == movie.getImdbId())
                    .findFirst();

            if (!movieFilter.isPresent()) {
                String[] linha = {
                        movieFilter.get().getImdbId(),
                        movieFilter.get().getTitle(),
                        movieFilter.get().getYear().toString()
                };

                this.arquivo.writeNext(linha);
            }
        }
    }
}
