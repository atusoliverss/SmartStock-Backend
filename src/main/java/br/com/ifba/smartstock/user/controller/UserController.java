package br.com.ifba.smartstock.user.controller;

import br.com.ifba.smartstock.infrastructure.mapper.ObjectMapperUntil;
import br.com.ifba.smartstock.user.dto.UserGetResponseDto;
import br.com.ifba.smartstock.user.dto.UserPostRequestDto;
import br.com.ifba.smartstock.user.entities.User; // Importação da entidade User, que representa os dados do usuário.
import br.com.ifba.smartstock.user.repository.UserRepository; // Importação do repositório para manipulação de dados do banco.
import br.com.ifba.smartstock.user.service.UserService;
import lombok.RequiredArgsConstructor; // Lombok para gerar um construtor com os atributos obrigatórios.
import org.springframework.http.HttpStatus; // Importação dos status HTTP usados nas respostas.
import org.springframework.http.MediaType; // Importação dos tipos de mídia para especificação no cabeçalho HTTP.
import org.springframework.http.ResponseEntity; // Classe para encapsular as respostas HTTP.
import org.springframework.web.bind.annotation.*; // Anotações do Spring para configurar controladores e endpoints REST.

@RestController // Indica que esta classe é um controlador REST, gerenciando requisições HTTP.
@RequestMapping(path = "/users") // Define a base do caminho de todas as rotas deste controlador.
@RequiredArgsConstructor // Gera automaticamente um construtor com base nos atributos finais (final).
public class UserController {

    // Injeção de dependência do repositório de usuários.
    private final UserService userService;
    private final ObjectMapperUntil objectMapperUntil;

    /**
     * Endpoint para buscar todos os usuários.
     * @return Lista de usuários no formato JSON, com status HTTP 200 (OK).
     */
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUntil.mapAll(userService.findAll(), UserGetResponseDto.class));
    }

    /**
     * Endpoint para salvar um novo usuário.
     * @param user Objeto User recebido no corpo da requisição no formato JSON.
     * @return O usuário salvo, com status HTTP 201 (Created).
     */
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UserPostRequestDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUntil.map(userService.save(objectMapperUntil.map(user, User.class)), UserGetResponseDto.class));
    }

    /**
     * Endpoint para deletar um usuário pelo ID.
     * @param id ID do usuário a ser deletado, recebido como variável de caminho.
     * @return Resposta sem conteúdo (HTTP 204 - No Content) após a exclusão.
     */
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint para atualizar um usuário existente.
     * @param user Objeto User recebido no corpo da requisição no formato JSON.
     * @return O usuário atualizado, com status HTTP 200 (OK).
     */
    public ResponseEntity<?> update(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUntil.map(userService.save(objectMapperUntil.map(user, User.class)), UserGetResponseDto.class));
    }

}
