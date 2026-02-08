package com.example.endterm.model;

public abstract class MenuItem {
    private Integer id;
    private String name;
    private double price;

    protected MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getType();
}
