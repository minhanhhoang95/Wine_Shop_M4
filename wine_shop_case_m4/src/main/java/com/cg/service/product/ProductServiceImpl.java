package com.cg.service.product;


import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductMedia;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.ProductListDTO;
import com.cg.model.enums.FileType;
import com.cg.repository.ProductMediaRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.category.CategoryService;
import com.cg.service.upload.UploadService;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductListDTO> findAllProductListDTO() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllProductsDTO() {
        return productRepository.findAllProductsDTO ();
    }

    @Override
    public List<ProductDTO> findAllProductsDTOTrash() {
        return productRepository.findAllProductsDTOTrash ();
    }

    @Override
    public Boolean existsByTitle(String title) {
        return productRepository.existsByTitle ( title );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save ( product );
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void remove(String id) {

    }


}
