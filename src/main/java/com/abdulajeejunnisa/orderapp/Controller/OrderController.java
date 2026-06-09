package com.abdulajeejunnisa.orderapp.Controller;

import com.abdulajeejunnisa.orderapp.model.Order;
import com.abdulajeejunnisa.orderapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(
            @RequestBody Order order) {

        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/assign")
    public Order assignOrder(
            @PathVariable Long id,
            @RequestParam Long driverId) {

        return orderService.assignOrder(
                id,
                driverId
        );
    }

    @PutMapping("/{id}/pickup")
    public Order pickupOrder(
            @PathVariable Long id) {

        return orderService.pickupOrder(id);
    }

    @PutMapping("/{id}/deliver")
    public Order deliverOrder(
            @PathVariable Long id) {

        return orderService.deliverOrder(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);

        return "Order Deleted Successfully";
    }
}