package com.example.endterm.controller;

import com.example.endterm.dto.OrderCreateRequest;
import com.example.endterm.dto.OrderResponse;
import com.example.endterm.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }
    @PostMapping
    public OrderResponse create(@RequestBody OrderCreateRequest req) {
        return service.create(req);
    }
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @GetMapping
    public List<OrderResponse> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        service.updateStatus(id, body.get("status"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
