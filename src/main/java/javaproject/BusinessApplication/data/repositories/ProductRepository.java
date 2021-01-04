package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    int deleteByTypeAndModel(String type,String model);

    Optional<Product> findByTypeAndModel(String type, String model);

    boolean existsByTypeAndModel(String type,String model);
}