package com.makeupp.makeupp.DTO;

import com.makeupp.makeupp.model.product;

public class cartDTO {
    private int cartId;
    private int userId;
    private int productId;
    private int stock;
    private product product; // Agregado para incluir el producto completo

    public cartDTO() {
    }

    // Constructor con producto
    public cartDTO(int cartId, int userId, product product, int stock) {
        this.cartId = cartId;
        this.userId = userId;
        this.product = product;
        this.productId = product.getProduct_id(); // también lo seteamos por compatibilidad
        this.stock = stock;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }
}
