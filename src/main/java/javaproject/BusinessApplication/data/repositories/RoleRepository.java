package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Role findByAuthority(String role);
}
