package javaproject.BusinessApplication.data.repositories;

import javaproject.BusinessApplication.data.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findBySellerName(String sellerName);
    List<Sale> findByDateBetween(Date from,Date to);
}
