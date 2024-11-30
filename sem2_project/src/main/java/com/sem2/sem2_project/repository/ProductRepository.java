package com.sem2.sem2_project.repository;

import com.sem2.sem2_project.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = """
            SELECT p FROM Product p ORDER BY p.id DESC 
            """)
    List<Product> getLimitedProducts(Pageable pageable);

    @Query("""
            SELECT p FROM Product p WHERE p.price BETWEEN :firstPrice AND :lastPrice
            """)
    List<Product> findProductByPrice(
            @Param("firstPrice") double firstPrice,
            @Param("lastPrice") double lastPrice);
}