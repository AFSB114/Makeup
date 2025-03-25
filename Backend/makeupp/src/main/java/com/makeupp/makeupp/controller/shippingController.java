package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.shippingDTO;
import com.makeupp.makeupp.service.shippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shipping")
public class shippingController {

    @Autowired
    private shippingService shippingService;

    @PostMapping("/")
    public ResponseEntity<Object> addShipping(@RequestBody shippingDTO shippingDTO) {
        responseDTO respuesta = shippingService.save(shippingDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllShippings() {
        var listaShippings = shippingService.findAll();
        return new ResponseEntity<>(listaShippings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShippingById(@PathVariable int id) {
        Optional<shippingDTO> shipping = shippingService.findById(id).map(shippingService::convertToDTO);
        if (!shipping.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShipping(@PathVariable int id) {
        var respuesta = shippingService.deleteShipping(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}