package com.example.endterm.service;

import com.example.endterm.dto.OrderCreateRequest;
import com.example.endterm.dto.OrderResponse;
import com.example.endterm.exception.BadRequestException;
import com.example.endterm.model.Order;
import com.example.endterm.patterns.LoggerSingleton;
import com.example.endterm.patterns.MenuItemFactory;
import com.example.endterm.repository.MenuItemRepository;
import com.example.endterm.repository.OrderItemRepository;
import com.example.endterm.repository.OrderRepository;
import com.example.endterm.utils.MoneyUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final MenuItemRepository menuItemRepo;

    public OrderService(OrderRepository orderRepo, OrderItemRepository orderItemRepo, MenuItemRepository menuItemRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.menuItemRepo = menuItemRepo;
    }

    public OrderResponse create(OrderCreateRequest req) {
        if (req == null) throw new BadRequestException("Body is required");
        if (req.customerName == null || req.customerName.isBlank())
            throw new BadRequestException("customerName is required");
        if (req.items == null || req.items.isEmpty())
            throw new BadRequestException("items must not be empty");
        var logger = LoggerSingleton.getInstance();
        double total = 0.0;
        List<Integer> menuIds = new ArrayList<>();
        for (var line : req.items) {
            if (line.quantity <= 0)
                throw new BadRequestException("quantity must be > 0");
            var mi = MenuItemFactory.create(line.type, line.name, line.price);
            double price = MoneyUtil.round(mi.getPrice());
            int menuItemId = menuItemRepo.create(mi.getType(), mi.getName(), price);
            menuIds.add(menuItemId);
            total += price * line.quantity;
        }
        total = MoneyUtil.round(total);
        Order order = Order.builder()
                .customerName(req.customerName)
                .status("CREATED")
                .totalPrice(total)
                .build();
        int orderId = orderRepo.create(order.getCustomerName(), order.getStatus(), order.getTotalPrice());
        logger.log("Created order id=" + orderId);
        for (int i = 0; i < req.items.size(); i++) {
            var line = req.items.get(i);
            int menuItemId = menuIds.get(i);
            orderItemRepo.addLine(
                    orderId,
                    menuItemId,
                    line.quantity,
                    MoneyUtil.round(line.price)
            );
        }
        return getById(orderId);
    }

    public OrderResponse getById(int id) {
        Map<String, Object> row = orderRepo.findOrderRow(id);
        List<Map<String, Object>> lines = orderItemRepo.findLinesByOrderId(id);
        Instant createdAt = ((Timestamp) row.get("created_at")).toInstant();
        var outLines = new ArrayList<OrderResponse.OrderLine>();
        for (var l : lines) {
            outLines.add(new OrderResponse.OrderLine(
                    (Integer) l.get("menu_item_id"),
                    (String) l.get("name"),
                    (String) l.get("type"),
                    ((Number) l.get("price")).doubleValue(),
                    (Integer) l.get("quantity")
            ));
        }
        return new OrderResponse(
                (Integer) row.get("id"),
                (String) row.get("customer_name"),
                (String) row.get("status"),
                ((Number) row.get("total_price")).doubleValue(),
                createdAt,
                outLines
        );
    }

    public List<OrderResponse> getAll() {
        List<Map<String, Object>> rows = orderRepo.findAllOrderRows();
        List<OrderResponse> out = new ArrayList<>();
        for (var r : rows) {
            out.add(getById((Integer) r.get("id")));
        }
        return out;
    }

    public void updateStatus(int id, String status) {
        if (status == null || status.isBlank())
            throw new BadRequestException("status is required");

        orderRepo.updateStatus(id, status.trim().toUpperCase());
        LoggerSingleton.getInstance().log("Updated order id=" + id + " status=" + status);
    }

    public void delete(int id) {
        orderRepo.delete(id);
        LoggerSingleton.getInstance().log("Deleted order id=" + id);
    }
}
