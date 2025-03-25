package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.ordersDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.service.ordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class ordersController {

    @Autowired
    private ordersService ordersService;

    @GetMapping("/")
    public ResponseEntity<List<ordersDTO>> getAllOrders() {
        List<ordersDTO> listaOrdenes = ordersService.findAll().stream()
                .map(ordersService::convertToDTO).toList();
        return new ResponseEntity<>(listaOrdenes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable int id) {
        var orden = ordersService.findById(id);
        if (orden.isEmpty()) {
            return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orden.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addOrder(@RequestBody ordersDTO order) {
        responseDTO respuesta = ordersService.save(order);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable int id) {
        responseDTO respuesta = ordersService.deleteOrder(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
