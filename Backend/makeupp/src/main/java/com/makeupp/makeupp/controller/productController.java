package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.productDTO;
import com.makeupp.makeupp.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class productController {

    @Autowired
    private productService productService;

    @PostMapping("/")
    public ResponseEntity<Object> addProduct(@RequestBody productDTO productDTO) {
        responseDTO respuesta = productService.save(productDTO);

        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
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
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(producto.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody productDTO productDTO) {
        responseDTO respuesta = productService.updateProduct(id, productDTO);

        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        var respuesta = productService.deleteProduct(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
