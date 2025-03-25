package com.makeupp.makeupp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity(name = "shipping")
public class shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private int shippingId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private orders order;

    @Column(length = 150)
    private String shippingAddress;

    @Column(length = 100)
    private String carrier;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date shippingDate;

    @Column(length = 50)
    private String shippingStatus;

    public shipping() {
    }


    public shipping(orders order, String shippingAddress, String carrier, Date shippingDate, String shippingStatus) {
        this.order = order;
        this.shippingAddress = shippingAddress;
        this.carrier = carrier;
        this.shippingDate = shippingDate;
        this.shippingStatus = shippingStatus;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public orders getOrder() {
        return order;
    }

    public void setOrder(orders order) {
        this.order = order;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
