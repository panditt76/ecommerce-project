package com.salman.ecommerce.repository;

import com.salman.ecommerce.entity.Product;
import com.salman.ecommerce.entity.ProductCategory;
import com.salman.ecommerce.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.productId AS productId, p.productName AS productName, p.quantity AS quantity, " +
            "p.status AS status FROM Product p")
    List<ProductView> getAll();

    @Query(value = "SELECT p.productId AS productId, p.productName AS productName, p.quantity AS quantity, " +
            "p.status AS status FROM Product p WHERE p.category = :category")
    List<ProductView> getAllByCategory(@Param("category") ProductCategory category);

    @Query(value = "SELECT p.productId AS productId, p.productName AS productName, p.quantity AS quantity, " +
            "p.status AS status FROM Product p " +
            "INNER JOIN Seller s ON s.sellerId = p.seller.sellerId " +
            "WHERE s.sellerId = :sellerId")
    List<ProductView> getAllBySellerId(@Param("sellerId") long sellerId);

    Optional<Product> findByProductIdAndSeller_SellerId(long productId, long sellerId);
}
