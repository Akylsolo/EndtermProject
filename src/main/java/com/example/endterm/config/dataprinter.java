package com.example.endterm.config;
import com.example.endterm.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class dataprinter {
    @Bean
    CommandLineRunner printOrders(OrderService orderService) {
        return args -> {
            System.out.println("=== ORDERS FROM DATABASE ===");
            orderService.getAll().forEach(System.out::println);
        };
    }
}
