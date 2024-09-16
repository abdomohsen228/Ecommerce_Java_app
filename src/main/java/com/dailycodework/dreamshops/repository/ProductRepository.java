package com.dailycodework.dreamshops.repository;

import com.dailycodework.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
// This annotation is not strictly necessary with Spring Data JPA because Spring automatically detects interfaces that extend JpaRepository as repositories
public interface ProductRepository extends JpaRepository<Product,Long> {
    // <entity manage, primary key>


    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String productName);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);

    boolean existsByNameAndBrand(String name, String brand);
}
