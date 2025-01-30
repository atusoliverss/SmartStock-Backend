package br.com.ifba.smartstock.infrastructure.exception;

import org.springframework.http.HttpStatus; // Representa códigos de status HTTP.
import org.springframework.http.ResponseEntity; // Encapsula a resposta HTTP.
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler; // Define métodos para lidar com exceções específicas.
import org.springframework.web.bind.annotation.ResponseStatus; // Define o código de status HTTP para exceções tratadas.
import org.springframework.web.bind.annotation.RestControllerAdvice; // Fornece manipulação global de exceções para controladores REST.
import org.springframework.web.context.request.WebRequest; // Representa a requisição web no momento do erro.
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler; // Classe base para manipulação de exceções em REST.

import java.time.LocalDateTime; // Representa a data e hora do erro.
import java.util.HashMap;
import java.util.LinkedHashMap; // Implementação de um mapa ordenado para armazenar detalhes do erro.
import java.util.List;
import java.util.Map; // Interface para armazenar pares chave-valor.
import java.util.stream.Collectors;

/**
 * Classe responsável por capturar e tratar exceções globais na aplicação.
 * Utiliza a anotação @RestControllerAdvice para interceptar exceções nos controllers REST.
 */

@RestControllerAdvice
public class ApiExceptionHandler{

    // Obtém o valor da propriedade 'server.error.include-exception' do arquivo application.properties.
    // Define se a pilha de erro será exibida na resposta.
//    @Value(value = "${server.error.include-exception}")
//    private  boolean printStackTrace;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        // Lista de campos inválidos
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // Mensagem detalhada para desenvolvedores
        String fieldsMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        // Criação do mapa de erro
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request, campos inválidos");
        errorResponse.put("message", "Campos com erro");
        errorResponse.put("developerMessage", fieldsMessage);
        errorResponse.put("fields", fields);

        // Retorno da resposta
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções do tipo BusinessException.
     * Retorna um erro HTTP 400 (Bad Request) com detalhes da exceção.
     *
     * @param businessException Exceção lançada no fluxo de negócio.
     * @param request Informações da requisição web onde o erro ocorreu.
     * @return ResponseEntity contendo os detalhes do erro.
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlerBusinessException(final BusinessException businessException, final WebRequest request) {
        final String errorMessage = businessException.getMessage();

        // Constrói e retorna a resposta com os detalhes do erro.
        return buildErrorMessage(businessException, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Método auxiliar para construir a resposta de erro.
     *
     * @param exception Exceção capturada.
     * @param message Mensagem descritiva do erro.
     * @param httpStatus Código de status HTTP apropriado.
     * @param request Informações da requisição web.
     * @return ResponseEntity contendo detalhes formatados do erro.
     */
    private ResponseEntity<Object> buildErrorMessage(
            final Exception exception, final String message, final HttpStatus httpStatus, final WebRequest request
    ) {
        // Mapa contendo os detalhes da resposta de erro.
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now()); // Data e hora do erro.
        errorDetails.put("status", httpStatus.value()); // Código de status HTTP.
        errorDetails.put("error", httpStatus.getReasonPhrase()); // Descrição do status HTTP.
        errorDetails.put("message", message); // Mensagem específica do erro.
        errorDetails.put("path", request.getDescription(false)); // Caminho da requisição que gerou o erro.
        errorDetails.put("exception", exception.getClass().getSimpleName()); // Nome da exceção capturada.

        // Retorna a resposta com o status HTTP e os detalhes do erro.
        return ResponseEntity.status(httpStatus).body(errorDetails);
    }
}
