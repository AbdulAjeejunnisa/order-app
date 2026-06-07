package com.abdulajeejunnisa.orderapp.Controller;

import com.abdulajeejunnisa.orderapp.dto.CreateOrderRequest;
import com.abdulajeejunnisa.orderapp.dto.UpdateLocationRequest;
import com.abdulajeejunnisa.orderapp.model.Order;
import com.abdulajeejunnisa.orderapp.model.OrderStatus;
import com.abdulajeejunnisa.orderapp.service.OrderService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Order> createOrder(
            @RequestBody CreateOrderRequest request) {

        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(
            @PathVariable OrderStatus status) {

        return orderService.getOrdersByStatus(status);
    }


    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }


    @PutMapping("/{orderId}/assign/{driverId}")
    public Order assignOrder(
            @PathVariable Long orderId,
            @PathVariable Long driverId) {

        return orderService.assignOrder(orderId, driverId);
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
    @PutMapping("/{orderId}/location")
    public ResponseEntity<Order> updateLocation(
            @PathVariable Long orderId,
            @RequestBody UpdateLocationRequest request) {

        return ResponseEntity.ok(
                orderService.updateLocation(
                        orderId,
                        request.getLocationId()
                )
        );
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);
        return "Order Deleted Successfully";
    }
}