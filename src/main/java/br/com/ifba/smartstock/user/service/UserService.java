package br.com.ifba.smartstock.user.service;

import br.com.ifba.smartstock.infrastructure.exception.BusinessException;
import br.com.ifba.smartstock.user.entities.User;
import br.com.ifba.smartstock.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço responsável pela lógica de negócios relacionada à entidade {@link User}.
 * Implementa a interface {@link UserIService}.
 */
@Service
@RequiredArgsConstructor // Gera um construtor automaticamente para os atributos finais (final).
public class UserService implements UserIService {

    // Repositório responsável pela persistência dos usuários.
    private final UserRepository userRepository;

    /**
     * Retorna uma lista de todos os usuários cadastrados.
     *
     * @return Lista de usuários.
     */
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id Identificador do usuário.
     * @return Usuário encontrado.
     * @throws BusinessException Caso o ID não seja encontrado.
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id não encontrado"));
    }

    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param user Objeto usuário a ser salvo.
     * @return Usuário salvo.
     */

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Exclui um usuário pelo ID.
     *
     * @param id Identificador do usuário a ser excluído.
     */
    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Atualiza um usuário existente no banco de dados.
     *
     * @param user Objeto usuário com as novas informações.
     * @return Usuário atualizado.
     */
    @Transactional
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
