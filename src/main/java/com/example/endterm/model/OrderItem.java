package com.example.endterm.model;

public class OrderItem {
    private int orderId;
    private int menuItemId;
    private int quantity;
    private double itemPrice;

    public OrderItem(int orderId, int menuItemId, int quantity, double itemPrice) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
