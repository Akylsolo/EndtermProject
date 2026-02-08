package com.example.endterm.controller;

import com.example.endterm.dto.MenuItemCreateRequest;
import com.example.endterm.dto.MenuItemResponse;
import com.example.endterm.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping
    public MenuItemResponse create(@RequestBody MenuItemCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<MenuItemResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
