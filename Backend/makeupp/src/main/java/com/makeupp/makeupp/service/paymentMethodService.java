package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.payment_methodDTO;
import com.makeupp.makeupp.model.payment_method;
import com.makeupp.makeupp.repository.Ipaymentmethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class paymentMethodService {

    @Autowired
    private Ipaymentmethod data;

    public List<payment_method> findAll() {
        return data.findAll();
    }

    public Optional<payment_method> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteById(int id) {
        if (!findById(id).isPresent()) {
            return new responseDTO(
                HttpStatus.OK.toString(),
                "El método de pago no existe"
            );
        }
        data.deleteById(id);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Método de pago eliminado correctamente"
        );
    }

    public responseDTO save(payment_methodDTO paymentMethodDTO) {
        if (paymentMethodDTO.getType().length() < 1 || paymentMethodDTO.getType().length() > 50) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El tipo de método de pago debe tener entre 1 y 50 caracteres"
            );
        }

        payment_method paymentMethodRegister = convertToModel(paymentMethodDTO);
        data.save(paymentMethodRegister);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Método de pago guardado correctamente"
        );
    }

    public payment_methodDTO convertToDTO(payment_method paymentMethod) {
        return new payment_methodDTO(
                paymentMethod.getPayment_method_id(),
                paymentMethod.getType()
        );
    }

    public payment_method convertToModel(payment_methodDTO paymentMethodDTO) {
        return new payment_method(
                paymentMethodDTO.getPayment_method_id(),
                paymentMethodDTO.getType()
        );
    }
}