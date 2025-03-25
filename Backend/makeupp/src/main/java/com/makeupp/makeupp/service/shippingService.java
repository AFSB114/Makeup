package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.shippingDTO;
import com.makeupp.makeupp.model.shipping;
import com.makeupp.makeupp.model.orders;
import com.makeupp.makeupp.repository.Ishipping;
import com.makeupp.makeupp.repository.Iorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class shippingService {
    
    @Autowired
    private Ishipping data;
    
    @Autowired
    private Iorder orderRepository;

    public List<shipping> findAll() {
        return data.findAll();
    }

    public Optional<shipping> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteShipping(int id) {
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

    public responseDTO save(shippingDTO shippingDTO) {
        if (shippingDTO.getShippingAddress().length() < 1 || shippingDTO.getShippingAddress().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La dirección de envío debe estar entre 1 y 100 caracteres"
            );
            return respuesta;
        }
        
        Optional<orders> order = orderRepository.findById(shippingDTO.getOrderId());
        if (order.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La orden asociada no existe"
            );
            return respuesta;
        }
        
        shipping shippingRegister = convertToModel(shippingDTO);
        data.save(shippingRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public shippingDTO convertToDTO(shipping shipping) {
        return new shippingDTO(
            shipping.getShippingId(),
            shipping.getOrder().getOrderId(),
            shipping.getShippingAddress(),
            shipping.getCarrier(),
            shipping.getShippingDate(),
            shipping.getShippingStatus()
        );
    }

    public shipping convertToModel(shippingDTO shippingDTO) {
        orders order = orderRepository.findById(shippingDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        return new shipping(
            order,
            shippingDTO.getShippingAddress(),
            shippingDTO.getCarrier(),
            shippingDTO.getShippingDate(),
            shippingDTO.getShippingStatus()
        );
    }
}
