package com.example.endterm.model;

import java.time.Instant;

public class Order {
    private Integer id;
    private String customerName;
    private String status;
    private double totalPrice;
    private Instant createdAt;

    private Order(Builder b) {
        this.id = b.id;
        this.customerName = b.customerName;
        this.status = b.status;
        this.totalPrice = b.totalPrice;
        this.createdAt = b.createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String customerName;
        private String status;
        private double totalPrice;
        private Instant createdAt = Instant.now();

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
