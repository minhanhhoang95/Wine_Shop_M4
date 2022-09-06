package com.cg.controller.rest;


import com.cg.exception.AttributesExistsException;
import com.cg.exception.DataInputException;
import com.cg.exception.DataOutputException;
import com.cg.exception.ResourceNotFoundException;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.*;

import com.cg.service.category.CategoryService;
import com.cg.service.product.ProductService;
import com.cg.utils.AppUtils;
import com.cg.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private AppUtils appUtil;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Validator validator;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> productDTOList = productService.findAllProductsDTO ();

        if (productDTOList.isEmpty ()) {
            throw new DataOutputException( "No data" );
        }

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/trash")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllProductTrash() {
        List<ProductDTO> products = productService.findAllProductsDTOTrash ();

        if (products.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {

        if ( !validator.isIntValid ( productId ) ) {
            throw new DataInputException ( "Product ID invalid!" );
        }
        Long product_id = Long.parseLong ( productId );

        Optional<Product> productOptional = productService.findById(product_id);

        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Product invalid");
        }

        return new ResponseEntity<>(productOptional.get().toProductDTO (), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doAddProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        String slug = Validator.makeSlug ( productDTO.getTitle () );



        try {
            Product product = productDTO.toProduct ();
            product.setSlug ( slug );

            Product newProduct = productService.save(product);

            return new ResponseEntity<>( newProduct.toProductDTO (), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException ("Product information is not valid, please check the information again");
        }
    }

    @PatchMapping("/remove")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doRemove(@Validated @RequestBody ProductDTO productDTO) {

        Optional<Product> productOptional = productService.findById(productDTO.getId ());

        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException ("User invalid");
        }

        Product product = productOptional.get();

        product.setDeleted ( true);
        productService.save ( product );

        Product productRemoved = productService.findById ( product.getId () ).get ();
        return new ResponseEntity<>(productRemoved.toProductDTO (), HttpStatus.OK);
    }

    @PatchMapping("/restore")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doRestore(@Validated @RequestBody ProductDTO productDTO) {

        Optional<Product> productOptional = productService.findById(productDTO.getId ());

        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException ("Product invalid");
        }

        Product product = productOptional.get();

        product.setDeleted ( false);
        productService.save ( product );

        Product productRestored = productService.findById ( product.getId () ).get ();
        return new ResponseEntity<>(productRestored.toProductDTO (), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doEditProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors())
            return appUtil.mapErrorToResponse(bindingResult);

        Optional<Product> productOptional = productService.findById ( productDTO.getId () );

        if ( !productOptional.isPresent () ) {
            throw new ResourceNotFoundException ( "Product not exists!" );
        }

        Boolean existsTitle = productService.existsByTitle ( productDTO.getTitle () );

        if (existsTitle && !productDTO.getTitle ().equals ( productOptional.get ().getTitle () ) ) {
            throw new AttributesExistsException("Title already exists");
        }

        try {


            Optional<Category> category = categoryService.findById ( productDTO.getCategory ().getId () );
            if ( !category.isPresent () ) {
                throw new DataInputException ( "Category not exists!" );
            }
            productDTO.setCategory(category.get().toCategoryDTO());
            Product product = productDTO.toProduct ();

            String slug = Validator.makeSlug ( productDTO.getTitle () );
            product.setSlug ( slug );


            Product newProduct = productService.save(product);

            return new ResponseEntity<>(newProduct.toProductDTO () ,HttpStatus.ACCEPTED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Product information is not valid, please check the information again");
        }
    }
}
