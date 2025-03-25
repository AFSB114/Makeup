package com.makeupp.makeupp.DTO;

public class payment_methodDTO {
    private int payment_method_id;
    private String type;

    public payment_methodDTO() {
    }

    public payment_methodDTO(int payment_method_id, String type) {
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
