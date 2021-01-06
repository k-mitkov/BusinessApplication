package javaproject.BusinessApplication.data.repositories;


import javaproject.BusinessApplication.data.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    int deleteByUsername(String username);
    boolean existsByUsername(String username);
}