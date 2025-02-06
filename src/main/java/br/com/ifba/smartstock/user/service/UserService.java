package br.com.ifba.smartstock.user.service;

import br.com.ifba.smartstock.infrastructure.exception.BusinessException;
import br.com.ifba.smartstock.user.dto.UserPutRequestDto;
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
     * @param email Email do usuário a ser excluído.
     */
    @Transactional
    @Override
    public void delete(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null){
            userRepository.deleteById(user.getId());
        }
    }


    /**
     * Atualiza um usuário existente no banco de dados com os novos dados fornecidos.
     *
     * @param user Objeto UserPutRequestDto contendo as novas informações do usuário.
     * @return O usuário atualizado após a persistência no banco de dados.
     * @throws BusinessException Se o usuário com o email fornecido não for encontrado.
     */
    @Transactional
    @Override
    public User update(UserPutRequestDto user) {
        // Busca o usuário no banco de dados pelo email fornecido
        User userFound = userRepository.findUserByEmail(user.getEmail());

        // Verifica se o usuário foi encontrado
        if (userFound != null) {
            // Atualiza os dados do usuário encontrado com as novas informações
            userFound.setName(user.getName());
            userFound.setLogin(user.getLogin());

            // Salva o usuário atualizado no banco de dados
            return userRepository.save(userFound);
        }

        // Lança uma exceção caso o usuário não seja encontrado
        throw new BusinessException("Usuário com Email: " + user.getEmail() + " não encontrado");
    }

    /**
     * Busca um usuário pelo login.
     *
     * @param login O login do usuário.
     * @return Um Optional contendo o usuário, caso exista.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * Busca um usuário pelo login e senha.
     *
     * @param login    O login do usuário.
     * @param password A senha do usuário.
     * @return Um Optional contendo o usuário, caso as credenciais estejam corretas.
     */
    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

}
