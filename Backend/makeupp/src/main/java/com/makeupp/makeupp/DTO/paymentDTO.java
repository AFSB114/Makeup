package com.makeupp.makeupp.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class paymentDTO {
    private int paymentId;
    private int orderId;
    private int paymentMethodId;
    private BigDecimal amount;
    private Date paymentDate;
    private String status;

    public paymentDTO() {
    }

    public paymentDTO(int paymentId, int orderId, int paymentMethodId, BigDecimal amount, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
