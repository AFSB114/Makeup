package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.order_detailDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.model.order_detail;
import com.makeupp.makeupp.service.order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order-detail")
public class order_detailController {

    @Autowired
    private order_detailService orderDetailService;

    /** Guardar un detalle de orden */
    @PostMapping("/")
    public ResponseEntity<Object> addOrderDetail(@RequestBody order_detailDTO orderDetailDTO) {
        responseDTO respuesta = orderDetailService.save(orderDetailDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    /** Obtener todos los detalles de orden */
    @GetMapping("/")
    public ResponseEntity<Object> getAllOrderDetails() {
        List<order_detail> listaDetalles = orderDetailService.findAll();
        return new ResponseEntity<>(listaDetalles, HttpStatus.OK);
    }

    /** Obtener un detalle de orden por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrderDetail(@PathVariable int id) {
        Optional<order_detail> detalle = orderDetailService.findById(id);
        if (!detalle.isPresent()) 
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(detalle, HttpStatus.OK);
    }

    /** Eliminar un detalle de orden */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderDetail(@PathVariable int id) {
        responseDTO respuesta = orderDetailService.deleteOrderDetail(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
