package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.userDTO;
import com.makeupp.makeupp.model.user;
import com.makeupp.makeupp.repository.Iuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {
    
    @Autowired
    private Iuser data;

    public List<user> findAll() {
        return data.findAll();
    }

    public Optional<user> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteUser(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El registro no existe"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se eliminó correctamente"
        );
        return respuesta;
    }

    public responseDTO save(userDTO userDTO) {
        if (userDTO.getName().length() < 1 || userDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre debe estar entre 1 y 50 caracteres"
            );
            return respuesta;
        }
        
        user userRegister = convertToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public userDTO convertToDTO(user user) {
        return new userDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress(),
                user.getPhone(),
                user.getRole()
        );
    }

    public user convertToModel(userDTO userDTO) {
        return new user(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getAddress(),
                userDTO.getPhone(),
                userDTO.getRole()
        );
    }
}
