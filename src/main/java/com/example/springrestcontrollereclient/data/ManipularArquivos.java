package com.example.springrestcontrollereclient.data;

import com.example.springrestcontrollereclient.movie.Movie;
import com.example.springrestcontrollereclient.movie.ResultadoBusca;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManipularArquivos {
    private List<Movie> movies;

    public ManipularArquivos() {
        pesquisarArquivo();
    }

    private void pesquisarArquivo() {
        String filePath = getFilePath("cache.csv");

        try(Stream<String> linhas = Files.lines(Path.of(filePath))) {
            this.movies = pegarMovie(linhas.collect(Collectors.toList()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Movie> pegarMovie(List<String> linhas) {
        List<Movie> filmes = new ArrayList<>();

        linhas.remove(0);
        for (String linha : linhas) {
            String[] split = linha.split(";");

            Movie movie = new Movie();
            movie.setImdbId(split[0]);
            movie.setTitle(split[1]);
            movie.setYear(split[2]);

            filmes.add(movie);
        }

        return filmes;
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
                    .filter(m -> m.getImdbId().equals(movie.getImdbId()))
                    .findFirst();

            if (!movieFilter.isPresent()) {
                addLinha(movie.convertToCsv());
            }
        }
    }

    private void addLinha(String linha) {
        String filePath = getFilePath("cache.csv");

        try(FileOutputStream arquivo = new FileOutputStream(String.valueOf(Path.of(filePath)), true)) {
            for (int i = 0; i < linha.length(); i++) {
                int c = linha.charAt(i);
                arquivo.write(c);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
