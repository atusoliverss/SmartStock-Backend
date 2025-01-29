package br.com.ifba.smartstock.infrastructure.exception;

import java.io.Serial;

/**
 * Exceção personalizada para representar erros de regra de negócio na aplicação.
 * Extende {@link RuntimeException}, permitindo que seja lançada sem a necessidade de tratamento obrigatório.
 */
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Identificador único para serialização da classe.

    /**
     * Construtor padrão sem mensagem ou causa.
     */
    public BusinessException() {
        super();
    }

    /**
     * Construtor que permite definir uma mensagem de erro.
     *
     * @param message Mensagem explicativa sobre o erro.
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Construtor que permite definir a causa do erro.
     *
     * @param cause Exceção original que causou este erro.
     */
    public BusinessException(final Throwable cause) {
        super(cause);
    }

    /**
     * Construtor que permite definir uma mensagem de erro e a causa do erro.
     *
     * @param message Mensagem explicativa sobre o erro.
     * @param cause Exceção original que causou este erro.
     */
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
