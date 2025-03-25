package com.makeupp.makeupp.DTO;

import java.math.BigDecimal;

public class order_detailDTO {
    private int detailId;
    private int orderId;
    private int productId;
    private int stock;
    private BigDecimal subtotal;

    public order_detailDTO() {
    }

    public order_detailDTO(int detailId, int orderId, int productId, int stock, BigDecimal subtotal) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.productId = productId;
        this.stock = stock;
        this.subtotal = subtotal;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
