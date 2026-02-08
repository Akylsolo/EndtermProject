package com.example.endterm.repository;
import com.example.endterm.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository {
    private final JdbcTemplate jdbc;
    public OrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public int create(String customerName, String status, double totalPrice) {
        Integer id = jdbc.queryForObject("""
            INSERT INTO orders(customer_name, status, total_price)
            VALUES (?, ?, ?)
            RETURNING id
            """, Integer.class, customerName, status, totalPrice);

        if (id == null) throw new RuntimeException("Failed to create order: returned id is null");
        return id;
    }

    public Map<String, Object> findOrderRow(int id) {
        List<Map<String, Object>> rows = jdbc.queryForList(
                "SELECT id, customer_name, status, total_price, created_at FROM orders WHERE id = ?",
                id
        );
        if (rows.isEmpty()) {
            throw new NotFoundException("Order not found: " + id);
        }
        return rows.get(0);
    }
    public List<Map<String, Object>> findAllOrderRows() {
        return jdbc.queryForList(
                "SELECT id, customer_name, status, total_price, created_at FROM orders ORDER BY id DESC"
        );
    }
    public void updateStatus(int id, String newStatus) {
        int updated = jdbc.update(
                "UPDATE orders SET status = ? WHERE id = ?",
                newStatus, id
        );
        if (updated == 0) {
            throw new NotFoundException("Order not found: " + id);
        }
    }
    public void delete(int id) {
        int updated = jdbc.update("DELETE FROM orders WHERE id = ?", id);
        if (updated == 0) {
            throw new NotFoundException("Order not found: " + id);
        }
    }
}
