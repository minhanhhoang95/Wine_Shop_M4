package com.cg.repository;


import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.ProductListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


//    @Query("SELECT NEW com.cg.model.dto.ProductListDTO (" +
//            "p.id, " +
//            "p.title, " +
//            "p.slug, " +
//            "p.image, " +
//            "p.price " +
//            ") " +
//            "FROM Product AS p")
//    List<ProductListDTO> findAllProductListDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.slug, " +
            "p.image, " +
            "p.price, " +
            "p.sold, " +
            "p.createdBy, " +
            "p.category " +
            ") " +
            "FROM Product AS p WHERE p.deleted = false")
    List<ProductDTO> findAllProductsDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.slug, " +
            "p.image, " +
            "p.price, " +
            "p.sold, " +
            "p.createdBy, " +
            "p.category " +
            ") " +
            "FROM Product AS p WHERE p.deleted = true ")
    List<ProductDTO> findAllProductsDTOTrash();

    Boolean existsByTitle(String title);
}
