package com.makeupp.makeupp.model;

import jakarta.persistence.*;

@Entity(name = "category")
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int category_id;

    @Column(nullable = false, length = 80)
    private String name;

    public category() {
    }

    public category(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
