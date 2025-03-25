package com.makeupp.makeupp.DTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ordersDTO {
    private int orderId;
    private int user_id;
    private LocalDate orderDate;
    private String status;
    private BigDecimal total;

    public ordersDTO() {
    }

    public ordersDTO(int orderId, int user_id, LocalDate orderDate, String status, BigDecimal total) {
        this.orderId = orderId;
        this.user_id = user_id;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
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
