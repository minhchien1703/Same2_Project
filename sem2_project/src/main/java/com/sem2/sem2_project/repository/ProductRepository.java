package com.sem2.sem2_project.repository;

import com.sem2.sem2_project.model.Category;
import com.sem2.sem2_project.model.Product;
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

    List<Product> findTop5ByOrderByIdDesc();

    @Query("""
            SELECT p FROM Product p WHERE p.price BETWEEN :firstPrice AND :lastPrice
            """)
    List<Product> findProductByPrice(
            @Param("firstPrice") double firstPrice,
            @Param("lastPrice") double lastPrice);

    @Query(value = """
                SELECT p FROM Product p WHERE p.type = :type
            """)
    List<Product> findProductByPopular(@Param("type") String type, Pageable pageable);

    List<Product> getProductsByCategory(Category category);

    @Query("SELECT p FROM Product p JOIN p.rooms r WHERE r.id = :roomId")
    List<Product> getProductsByRoomId(@Param("roomId") int roomId);
}
