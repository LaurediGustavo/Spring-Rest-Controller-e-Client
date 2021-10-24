# Spring-Rest-Controller-e-Client

Crie uma aplicação Spring Boot que expõe um endpoint Rest para consultar filmes no IMDB. Cada resultado deve ser armazenado em um arquivo csv para servir como repositório primário da busca. Na busca seguinte, a aplicação deve buscar primeiro no csv e, caso não encontre, deve buscar novamente no IMDB.

O endpoint deve ser "/search?title="
O nome do csv deve ser "cache.csv"
Cada linha do csv deve conter todas as informações de um dos resultados retornados
Use as dependências: spring web, open feign e lombok
Ponto extra: crie um endpoint "/movies/{id}" para, ao informar o imdbId de um filme ou série, sejam exibidos todos os detalhes do conteúdo.
