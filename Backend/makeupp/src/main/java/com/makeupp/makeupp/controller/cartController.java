package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.cartDTO;
import com.makeupp.makeupp.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class cartController {

    @Autowired
    private cartService cartService;

    @PostMapping("/")
    public ResponseEntity<Object> addToCart(@RequestBody cartDTO cartDTO) {
        try {
            cartService.save(cartDTO);
            return new ResponseEntity<>("Producto agregado al carrito", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCarts() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartById(@PathVariable int id) {
        var cart = cartService.findById(id);
        return cart.isPresent()
            ? new ResponseEntity<>(cart, HttpStatus.OK)
            : new ResponseEntity<>("Carrito no encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable int id) {
        boolean deleted = cartService.deleteCart(id);
        return deleted
            ? new ResponseEntity<>("Carrito eliminado", HttpStatus.OK)
            : new ResponseEntity<>("Carrito no encontrado", HttpStatus.NOT_FOUND);
    }
}
