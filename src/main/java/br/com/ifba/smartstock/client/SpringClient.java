package br.com.ifba.smartstock.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe responsável por realizar requisições HTTP para o servidor usando WebClient.
 */
@Log4j2 // Anotação do Lombok para habilitar logging com Log4j2.
public class SpringClient {

    public static void main(String[] args) {
        // Criação de um cliente WebClient para comunicação com a API.
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/users/") // Define a URL base do servidor.
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Define o tipo de conteúdo como JSON.
                .build();


        // Realiza uma requisição GET para a rota "/findall".
        String response = webClient.get()
                .uri("/findall") // Define o endpoint da requisição.
                .retrieve() // Recupera a resposta da requisição.
                .bodyToMono(String.class) // Converte o corpo da resposta para uma String de forma reativa.
                .block(); // Bloqueia a execução até receber a resposta.

        // Registra a resposta da API no log.
        log.info(response);
    }
}
