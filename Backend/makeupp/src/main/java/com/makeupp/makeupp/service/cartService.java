package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.cartDTO;
import com.makeupp.makeupp.model.cart;
import com.makeupp.makeupp.model.user;
import com.makeupp.makeupp.model.product;
import com.makeupp.makeupp.repository.Icart;
import com.makeupp.makeupp.repository.Iuser;
import com.makeupp.makeupp.repository.Iproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class cartService {

    @Autowired
    private Icart data;

    @Autowired
    private Iuser userRepository;

    @Autowired
    private Iproduct productRepository;

    public void save(cartDTO cartDTO) {
        Optional<user> userOpt = userRepository.findById(cartDTO.getUserId());
        Optional<product> productOpt = productRepository.findById(cartDTO.getProductId());

        if (!userOpt.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (!productOpt.isPresent()) {
            throw new RuntimeException("Producto no encontrado");
        }

        cart cart = new cart(
                userOpt.get(),
                productOpt.get(),
                cartDTO.getStock()
        );

        data.save(cart);
    }

    public Iterable<cart> findAll() {
        return data.findAll();
    }

    public Optional<cart> findById(int id) {
        return data.findById(id);
    }

    public boolean deleteCart(int id) {
        if (data.existsById(id)) {
            data.deleteById(id);
            return true;
        }
        return false;
    }

    public List<cartDTO> getCartByUserId(int userId) {
        List<cart> carts = data.findByUser_Id(userId);
    
        return carts.stream().map(cart -> new cartDTO(
            cart.getCart_id(),
            cart.getUser().getId(),
            cart.getProduct(),
            cart.getStock()
        )).collect(Collectors.toList());
    }
}
