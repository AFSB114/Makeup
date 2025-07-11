package com.makeupp.makeupp.DTO;

public class userDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String role;
    private String password;
    private boolean status;
    
    public userDTO() {
    }

    public userDTO(String name, String email, String address, String phone, String role, String password, boolean status) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
