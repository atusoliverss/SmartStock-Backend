package br.com.ifba.smartstock.user.service;

import br.com.ifba.smartstock.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserIService {
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    User save(User user);
    void delete(Long id);
    User update(User user);
}
