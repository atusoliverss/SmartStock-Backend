package br.com.ifba.smartstock.user.repository;

import br.com.ifba.smartstock.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
