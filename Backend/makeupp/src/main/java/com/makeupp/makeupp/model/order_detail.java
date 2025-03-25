package com.makeupp.makeupp.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity(name = "order_detail")
public class order_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private int detail_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private product product;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    public order_detail() {}

    public order_detail(orders orders, product product, int stock, BigDecimal subtotal) {
        this.orders = orders;
        this.product = product;
        this.stock = stock;
        this.subtotal = subtotal;
    }

    public int getDetail_id() { 
        return detail_id; 
    }
    public void setDetail_id(int detail_id) { 
        this.detail_id = detail_id; 
    }
    public orders getOrder() { 
        return orders; 
    }
    public void setOrder(orders orders) { 
        this.orders = orders; 
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
    public BigDecimal getSubtotal() { 
        return subtotal; 
    }
    public void setSubtotal(BigDecimal subtotal) { 
        this.subtotal = subtotal; 
    }
}
