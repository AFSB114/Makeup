package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.userDTO;
import com.makeupp.makeupp.service.userService;
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
@RequestMapping("/api/v1/user")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody userDTO user) {
        responseDTO respuesta = userService.save(user);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        var listaUsuarios = userService.findAll();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var usuario = userService.findById(id);
        if (!usuario.isPresent()) 
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        var respuesta = userService.deleteUser(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
