package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.paymentDTO;
import com.makeupp.makeupp.model.payment;
import com.makeupp.makeupp.model.orders;
import com.makeupp.makeupp.model.payment_method;
import com.makeupp.makeupp.repository.Ipayment;
import com.makeupp.makeupp.repository.Iorder;
import com.makeupp.makeupp.repository.Ipaymentmethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class paymentService {
    
    @Autowired
    private Ipayment data;

    @Autowired
    private Iorder orderRepository;

    @Autowired
    private Ipaymentmethod paymentMethodRepository;

    public List<payment> findAll() {
        return data.findAll();
    }

    public Optional<payment> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deletePayment(int id) {
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

    public responseDTO save(paymentDTO paymentDTO) {
        if (paymentDTO.getAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El monto debe ser mayor a cero"
            );
        }
        
        Optional<orders> order = orderRepository.findById(paymentDTO.getOrderId());
        Optional<payment_method> paymentMethod = paymentMethodRepository.findById(paymentDTO.getPaymentMethodId());
        
        if (!order.isPresent() || !paymentMethod.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Orden o método de pago no encontrado"
            );
        }
        
        payment paymentRegister = convertToModel(paymentDTO, order.get(), paymentMethod.get());
        data.save(paymentRegister);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
    }

    public paymentDTO convertToDTO(payment payment) {
        return new paymentDTO(
                payment.getPayment_id(),
                payment.getOrder().getOrderId(),
                payment.getPaymentMethod().getPayment_method_id(),
                payment.getAmount(),
                payment.getPayment_date(),
                payment.getStatus()
        );
    }

    public payment convertToModel(paymentDTO paymentDTO, orders order, payment_method paymentMethod) {
        return new payment(
                order,
                paymentMethod,
                paymentDTO.getAmount(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getStatus()
        );
    }
}