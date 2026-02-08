package com.example.endterm.repository;
import com.example.endterm.dto.MenuItemResponse;
import com.example.endterm.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class MenuItemRepository {
    private final JdbcTemplate jdbc;
    public MenuItemRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public int create(String type, String name, double price) {
        Integer id = jdbc.queryForObject("""
            INSERT INTO menu_item(type, name, price)
            VALUES (?, ?, ?)
            RETURNING id
            """, Integer.class, type, name, price);

        if (id == null) throw new RuntimeException("Failed to create menu item: returned id is null");
        return id;
    }
    public List<MenuItemResponse> findAll() {
        return jdbc.query(
                "SELECT id, type, name, price FROM menu_item ORDER BY id",
                (rs, rowNum) -> new MenuItemResponse(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDouble("price")
                )
        );
    }
    public MenuItemResponse findById(int id) {
        List<MenuItemResponse> rows = jdbc.query(
                "SELECT id, type, name, price FROM menu_item WHERE id = ?",
                (rs, rowNum) -> new MenuItemResponse(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ),
                id
        );
        if (rows.isEmpty()) {
            throw new NotFoundException("Menu item not found: " + id);
        }

        return rows.get(0);
    }
    public void delete(int id) {
        int updated = jdbc.update("DELETE FROM menu_item WHERE id = ?", id);
        if (updated == 0) {
            throw new NotFoundException("Menu item not found: " + id);
        }
    }
}
