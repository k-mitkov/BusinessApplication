package javaproject.BusinessApplication.data.repositories;


import javaproject.BusinessApplication.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);
    int deleteByUsername(String userName);
}