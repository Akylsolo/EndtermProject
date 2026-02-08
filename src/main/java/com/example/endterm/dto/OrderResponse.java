package com.example.endterm.dto;

import java.time.Instant;
import java.util.List;
public class OrderResponse {
    public int id;
    public String customerName;
    public String status;
    public double totalPrice;
    public Instant createdAt;
    public List<OrderLine> items;
    public OrderResponse(int id, String customerName, String status, double totalPrice,
                         Instant createdAt, List<OrderLine> items) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.items = items;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", items=" + items +
                '}';
    }
    public static class OrderLine {
        public int menuItemId;
        public String name;
        public String type;
        public double price;
        public int quantity;
        public OrderLine(int menuItemId, String name, String type, double price, int quantity) {
            this.menuItemId = menuItemId;
            this.name = name;
            this.type = type;
            this.price = price;
            this.quantity = quantity;
        }
        @Override
        public String toString() {
            return "Item{" +
                    "menuItemId=" + menuItemId +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
