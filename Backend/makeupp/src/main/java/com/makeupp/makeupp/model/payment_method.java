package com.makeupp.makeupp.model;

import jakarta.persistence.*;

@Entity(name = "payment_method")
public class payment_method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int payment_method_id;

    @Column(nullable = false, length = 120)
    private String type;

    public payment_method() {
    }

    public payment_method(int payment_method_id, String type) {
        this.payment_method_id = payment_method_id;
        this.type = type;
    }

    public int getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
