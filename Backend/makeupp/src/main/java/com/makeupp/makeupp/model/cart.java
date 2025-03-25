package com.makeupp.makeupp.model;

import jakarta.persistence.*;

@Entity(name = "cart")
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cart_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private product product;

    @Column(name = "stock", nullable = false)
    private int stock;

    public cart() {
    }

    public cart(user user, product product, int stock) {
        this.user = user;
        this.product = product;
        this.stock = stock;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
