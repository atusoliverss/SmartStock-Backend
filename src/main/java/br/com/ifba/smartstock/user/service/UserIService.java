package br.com.ifba.smartstock.user.service;

import br.com.ifba.smartstock.user.dto.UserPutRequestDto;
import br.com.ifba.smartstock.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserIService {
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    User save(User user);
    void delete(String login);
    User update(UserPutRequestDto user);

    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndPassword(String login, String password);
}
