package com.sem2.sem2_project.repository;

import com.sem2.sem2_project.model.Product;
import com.sem2.sem2_project.repository.projection.ProductProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p.*, i.url from products p join images i on p.id = i.product_id order by p.id desc ", nativeQuery = true)
    List<Product> getLimitedProducts();

    @Query("""
            SELECT p FROM Product p WHERE p.price BETWEEN :firstPrice AND :lastPrice
            """)
    List<Product> findProductByPrice(
            @Param("firstPrice") double firstPrice,
            @Param("lastPrice") double lastPrice);
}
