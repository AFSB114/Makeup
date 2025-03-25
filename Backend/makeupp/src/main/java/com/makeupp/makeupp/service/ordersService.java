package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.ordersDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.model.orders;
import com.makeupp.makeupp.model.user;
import com.makeupp.makeupp.repository.Iorder;
import com.makeupp.makeupp.repository.Iuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ordersService {
    
    @Autowired
    private Iorder data;

    @Autowired
    private Iuser userRepository;

    public List<orders> findAll() {
        return data.findAll();
    }

    public Optional<orders> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteOrder(int id) {
        if (!findById(id).isPresent()) {
            return new responseDTO(
                HttpStatus.OK.toString(),
                "El registro no existe"
            );
        }
        data.deleteById(id);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Se eliminó correctamente"
        );
    }

    public responseDTO save(ordersDTO ordersDTO) {
        Optional<user> userOpt = userRepository.findById(ordersDTO.getUserId());
        if (userOpt.isEmpty()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Usuario no encontrado");
        }
        
        orders orderRegister = convertToModel(ordersDTO, userOpt.get());
        data.save(orderRegister);
        
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
    }

    public ordersDTO convertToDTO(orders order) {
        return new ordersDTO(
                order.getOrderId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotal()
        );
    }

    public orders convertToModel(ordersDTO ordersDTO, user user) {
        return new orders(
                user,
                ordersDTO.getOrderDate(),
                ordersDTO.getStatus(),
                ordersDTO.getTotal()
        );
    }
}