package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.model.*;
import com.abdulajeejunnisa.orderapp.exception.InvalidOrderStateException;
import com.abdulajeejunnisa.orderapp.exception.OrderNotFoundException;
import com.abdulajeejunnisa.orderapp.repository.DriverRepository;
import com.abdulajeejunnisa.orderapp.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.abdulajeejunnisa.orderapp.webSocket.StatusNotifier;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final StatusNotifier statusNotifier;

    public OrderService(
            OrderRepository orderRepository,
            DriverRepository driverRepository,
            StatusNotifier statusNotifier) {

        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.statusNotifier = statusNotifier;
    }

    public Order createOrder(Order order) {

        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order Not Found"));
    }

    public Order assignOrder(
            Long orderId,
            Long driverId) {

        Order order = getOrderById(orderId);

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Driver Not Found"));

        order.setDriver(driver);

        order.setStatus(OrderStatus.ASSIGNED);

        statusNotifier.notifyStatusChange(
                "Order " + orderId +
                        " ASSIGNED");

        return orderRepository.save(order);
    }

    public Order pickupOrder(Long orderId) {

        Order order = getOrderById(orderId);

        if(order.getStatus()
                != OrderStatus.ASSIGNED) {

            throw new InvalidOrderStateException(
                    "Order must be ASSIGNED first");
        }

        order.setStatus(OrderStatus.PICKED_UP);

        statusNotifier.notifyStatusChange(
                "Order " + orderId +
                        " PICKED UP");

        return orderRepository.save(order);
    }

    public Order deliverOrder(Long orderId) {

        Order order = getOrderById(orderId);

        if(order.getStatus()
                != OrderStatus.PICKED_UP) {

            throw new InvalidOrderStateException(
                    "Cannot deliver before pickup");
        }

        order.setStatus(OrderStatus.DELIVERED);

        statusNotifier.notifyStatusChange(
                "Order " + orderId +
                        " DELIVERED");

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {

        orderRepository.deleteById(id);
    }
}