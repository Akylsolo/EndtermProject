package com.example.endterm.dto;

public class MenuItemResponse {
    public int id;
    public String type;
    public String name;
    public double price;

    public MenuItemResponse(int id, String type, String name, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
