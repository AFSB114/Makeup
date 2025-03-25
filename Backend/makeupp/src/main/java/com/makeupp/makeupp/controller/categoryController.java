package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.categoryDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.model.category;
import com.makeupp.makeupp.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class categoryController {

    @Autowired
    private categoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Object> addCategory(@RequestBody categoryDTO category) {
        responseDTO respuesta = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta); 
    }

    @GetMapping("/")
    public ResponseEntity<List<category>> getAllCategories() {
        List<category> categorias = categoryService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        Optional<category> categoria = categoryService.findById(id);
        if (!categoria.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new responseDTO(String.valueOf(HttpStatus.NOT_FOUND.value()), "Category not found")); 
        }
        return ResponseEntity.ok(categoria.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        responseDTO respuesta = categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
