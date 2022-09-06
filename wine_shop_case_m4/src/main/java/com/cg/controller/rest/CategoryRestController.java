package com.cg.controller.rest;


import com.cg.model.dto.CategoryDTO;
import com.cg.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoryDTO();

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
}
