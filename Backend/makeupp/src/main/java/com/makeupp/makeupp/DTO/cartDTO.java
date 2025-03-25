package com.makeupp.makeupp.DTO;

public class cartDTO {
    private int cartId;
    private int userId;
    private int productId;
    private int stock;

    public cartDTO() {
    }

    public cartDTO(int cartId, int userId, int productId, int stock) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
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
}
