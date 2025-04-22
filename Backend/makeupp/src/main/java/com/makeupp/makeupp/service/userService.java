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

    public user login(String email, String password) {
        return data.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

    public List<user> findAll() {
        return data.getListUserActive();
    }

    public Optional<user> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteUser(int id) {
        Optional<user> usuario = findById(id);

        if (!usuario.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El usuario no existe");
        }

        try {
            data.eliminar(id);
            return new responseDTO(HttpStatus.OK.toString(), "Usuario eliminado correctamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al eliminar usuario: " + e.getMessage());
        }
    }

    public responseDTO save(userDTO userDTO) {
        if (userDTO.getName().length() < 1 || userDTO.getName().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe estar entre 1 y 50 caracteres");
        }

        user userRegister = convertToModel(userDTO);
        data.save(userRegister);
        return new responseDTO(HttpStatus.CREATED.toString(), "Se guardó correctamente");
    }

    public responseDTO update(int id, userDTO userDTO) {
        Optional<user> optionalUser = findById(id);

        if (!optionalUser.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        user user = optionalUser.get();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());

        data.save(user);

        return new responseDTO(HttpStatus.OK.toString(), "Usuario actualizado correctamente");
    }

    public userDTO convertToDTO(user user) {
        return new userDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress(),
                user.getPhone(),
                user.getRole(),
                user.getStatus()
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
