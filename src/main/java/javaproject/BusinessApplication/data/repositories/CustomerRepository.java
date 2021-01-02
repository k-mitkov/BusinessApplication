package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    int deleteByName(String customerName);
}