package com.makeupp.makeupp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "payment")
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int payment_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private orders orders;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private payment_method paymentMethod;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payment_date;

    @Column(length = 50)
    private String status;

    public payment() {
    }

    public payment(orders orders, payment_method paymentMethod, BigDecimal amount, Date payment_date, String status) {
        this.orders = orders;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.payment_date = payment_date;
        this.status = status;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public orders getOrder() {
        return orders;
    }

    public void setOrder(orders orders) {
        this.orders = orders;
    }

    public payment_method getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(payment_method paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
