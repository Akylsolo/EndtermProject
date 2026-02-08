package com.example.endterm.service;

import com.example.endterm.dto.MenuItemCreateRequest;
import com.example.endterm.dto.MenuItemResponse;
import com.example.endterm.patterns.LoggerSingleton;
import com.example.endterm.patterns.MenuItemFactory;
import com.example.endterm.repository.MenuItemRepository;
import com.example.endterm.utils.MoneyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository repo;

    public MenuItemService(MenuItemRepository repo) {
        this.repo = repo;
    }

    public MenuItemResponse create(MenuItemCreateRequest req) {
        var logger = LoggerSingleton.getInstance();
        var item = MenuItemFactory.create(req.type, req.name, req.price);
        double price = MoneyUtil.round(item.getPrice());
        int id = repo.create(item.getType(), item.getName(), price);
        logger.log("Created menu item id=" + id);
        return new MenuItemResponse(id, item.getType(), item.getName(), price);
    }

    public List<MenuItemResponse> getAll() {
        return repo.findAll();
    }

    public void delete(int id) {
        repo.delete(id);
        LoggerSingleton.getInstance().log("Deleted menu item id=" + id);
    }
}
