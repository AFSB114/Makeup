package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.order_detailDTO;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.model.order_detail;
import com.makeupp.makeupp.model.orders;
import com.makeupp.makeupp.model.product;
import com.makeupp.makeupp.repository.Iordendetail;
import com.makeupp.makeupp.repository.Iorder;
import com.makeupp.makeupp.repository.Iproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class order_detailService {

    @Autowired
    private Iordendetail data;

    @Autowired
    private Iorder orderRepository;

    @Autowired
    private Iproduct productRepository;

    /** Obtener todos los detalles de orden */
    public List<order_detail> findAll() {
        return data.findAll();
    }

    /** Obtener un detalle de orden por ID */
    public Optional<order_detail> findById(int id) {
        return data.findById(id);
    }

    /** Eliminar un detalle de orden */
    public responseDTO deleteOrderDetail(int id) {
        if (!findById(id).isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El detalle de orden no existe"
            );
        }
        data.deleteById(id);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Se eliminó correctamente"
        );
    }

    /** Guardar un nuevo detalle de orden */
    public responseDTO save(order_detailDTO orderDetailDTO) {
        // Verificar si la orden existe
        Optional<orders> orderOptional = orderRepository.findById(orderDetailDTO.getOrderId());
        if (!orderOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "La orden no existe");
        }

        // Verificar si el producto existe
        Optional<product> productOptional = productRepository.findById(orderDetailDTO.getProductId());
        if (!productOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El producto no existe");
        }

        // Validación del stock
        if (orderDetailDTO.getStock() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El stock debe ser mayor a 0");
        }

        // Convertir DTO a entidad y guardar
        order_detail orderDetail = convertToModel(orderDetailDTO, orderOptional.get(), productOptional.get());
        data.save(orderDetail);
        return new responseDTO(HttpStatus.OK.toString(), "Detalle de orden guardado correctamente");
    }

    /** Convertir de entidad a DTO */
    public order_detailDTO convertToDTO(order_detail orderDetail) {
        return new order_detailDTO(
                orderDetail.getDetail_id(),
                orderDetail.getOrder().getOrderId(),
                orderDetail.getProduct().getProduct_id(),
                orderDetail.getStock(),
                orderDetail.getSubtotal()
        );
    }

    /** Convertir de DTO a entidad */
    private order_detail convertToModel(order_detailDTO orderDetailDTO, orders order, product product) {
        return new order_detail(
                order,
                product,
                orderDetailDTO.getStock(),
                orderDetailDTO.getSubtotal()
        );
    }
}
