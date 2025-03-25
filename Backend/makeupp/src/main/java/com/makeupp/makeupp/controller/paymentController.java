package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.paymentDTO;
import com.makeupp.makeupp.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class paymentController {

    @Autowired
    private paymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<Object> addPayment(@RequestBody paymentDTO paymentDTO) {
        responseDTO respuesta = paymentService.save(paymentDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPayments() {
        var listaPagos = paymentService.findAll();
        return new ResponseEntity<>(listaPagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePayment(@PathVariable int id) {
        var pago = paymentService.findById(id);
        if (!pago.isPresent()) 
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable int id) {
        var respuesta = paymentService.deletePayment(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
