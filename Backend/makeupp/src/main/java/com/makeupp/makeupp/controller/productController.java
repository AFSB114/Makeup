package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.productDTO;
import com.makeupp.makeupp.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/products")
public class productController {

    @Autowired
    private productService productService;

    @PostMapping("/")
    public ResponseEntity<Object> addProduct(@RequestBody productDTO productDTO) {
        responseDTO respuesta = productService.save(productDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProducts() {
        var listaProductos = productService.findAll();
        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable int id) {
        var producto = productService.findById(id);
        if (!producto.isPresent()) 
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        var respuesta = productService.deleteProduct(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
