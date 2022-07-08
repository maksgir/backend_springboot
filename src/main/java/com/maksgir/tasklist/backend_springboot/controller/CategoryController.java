package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Category;
import com.maksgir.tasklist.backend_springboot.entity.Priority;
import com.maksgir.tasklist.backend_springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping()
    public List<Category> showAllCategories() {
        return categoryRepository.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id must be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;
        if (optional.isPresent()) {
            category = optional.get();
        } else {
            return new ResponseEntity("Category with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;
        if (optional.isPresent()) {
            categoryRepository.deleteById(id);
            category = optional.get();
        } else {
            return new ResponseEntity("Category with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }

}
