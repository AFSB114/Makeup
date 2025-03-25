package com.makeupp.makeupp.DTO;

import java.util.Date;

public class shippingDTO {
    private int shippingId;
    private int orderId;
    private String shippingAddress;
    private String carrier;
    private Date shippingDate;
    private String shippingStatus;

    public shippingDTO() {
    }

    public shippingDTO(int shippingId, int orderId, String shippingAddress, String carrier, Date shippingDate, String shippingStatus) {
        this.shippingId = shippingId;
        this.orderId = orderId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
