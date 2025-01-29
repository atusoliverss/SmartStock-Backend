package br.com.ifba.smartstock.user.service;

import br.com.ifba.smartstock.user.entities.User;

import java.util.List;

public interface UserIService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);
    User update(User user);
}
