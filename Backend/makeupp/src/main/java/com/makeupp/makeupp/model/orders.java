package com.makeupp.makeupp.model;

import java.time.LocalDate;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity(name = "orders") 
public class orders { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id; 

    @ManyToOne
    @JoinColumn(name = "id") 
    private user user; 

    @Column(nullable = false)
    private LocalDate orderDate; 

    @Column(length = 80)
    private String status;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    public orders() {
    }

    public orders(user user, LocalDate orderDate, String status, BigDecimal total) {
        this.user = user;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
