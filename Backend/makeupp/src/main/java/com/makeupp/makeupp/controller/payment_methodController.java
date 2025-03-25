package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.payment_methodDTO;
import com.makeupp.makeupp.service.paymentMethodService;
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
@RequestMapping("/api/v1/payment-method")
public class payment_methodController {

    @Autowired
    private paymentMethodService paymentMethodService;

    @PostMapping("/")
    public ResponseEntity<Object> addPaymentMethod(@RequestBody payment_methodDTO paymentMethod) {
        responseDTO respuesta = paymentMethodService.save(paymentMethod);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPaymentMethods() {
        var listaMetodos = paymentMethodService.findAll();
        return new ResponseEntity<>(listaMetodos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePaymentMethod(@PathVariable int id) {
        var metodo = paymentMethodService.findById(id);
        if (!metodo.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(metodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaymentMethod(@PathVariable int id) {
        var respuesta = paymentMethodService.deleteById(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
