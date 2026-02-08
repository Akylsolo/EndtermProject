package com.example.endterm.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderItemRepository {
    private final JdbcTemplate jdbc;

    public OrderItemRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addLine(int orderId, int menuItemId, int quantity, double itemPrice) {
        jdbc.update(
                "INSERT INTO order_item(order_id, menu_item_id, quantity, item_price) VALUES (?, ?, ?, ?)",
                orderId, menuItemId, quantity, itemPrice
        );
    }

    public List<Map<String, Object>> findLinesByOrderId(int orderId) {
        return jdbc.queryForList("""
                SELECT oi.menu_item_id, oi.quantity, oi.item_price,
                       mi.name, mi.type, mi.price
                FROM order_item oi
                JOIN menu_item mi ON mi.id = oi.menu_item_id
                WHERE oi.order_id = ?
                ORDER BY mi.id
                """, orderId);
    }
}
