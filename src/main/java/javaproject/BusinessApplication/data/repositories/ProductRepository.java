package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    int deleteByTypeAndModel(String type,String model);

    Product findByTypeAndModel(String type,String model);
}