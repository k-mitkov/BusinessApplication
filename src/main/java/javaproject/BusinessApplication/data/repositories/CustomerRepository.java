package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    int deleteByName(String customerName);
    boolean existsByName(String customerName);
    Optional<Customer> findByName(String customerName);
}