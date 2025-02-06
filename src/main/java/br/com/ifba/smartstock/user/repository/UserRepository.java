package br.com.ifba.smartstock.user.repository;

import br.com.ifba.smartstock.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("select u from User u where u.login = ?1")
    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

    User findUserByEmail(String email);
}
