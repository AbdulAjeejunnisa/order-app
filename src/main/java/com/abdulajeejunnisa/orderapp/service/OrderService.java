package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.dto.CreateOrderRequest;
import com.abdulajeejunnisa.orderapp.model.*;
import com.abdulajeejunnisa.orderapp.exception.InvalidOrderStateException;
import com.abdulajeejunnisa.orderapp.exception.OrderNotFoundException;
import com.abdulajeejunnisa.orderapp.repository.CustomerRepository;
import com.abdulajeejunnisa.orderapp.repository.DriverRepository;
import com.abdulajeejunnisa.orderapp.repository.LocationRepository;
import com.abdulajeejunnisa.orderapp.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.abdulajeejunnisa.orderapp.webSocket.StatusNotifier;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final StatusNotifier statusNotifier;
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, DriverRepository driverRepository, StatusNotifier statusNotifier, LocationRepository locationRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.statusNotifier = statusNotifier;
        this.locationRepository = locationRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Order order = new Order();

        order.setItemName(request.getItemName());
        order.setQuantity(request.getQuantity());

        order.setStatus(OrderStatus.CREATED);

        order.setCustomer(customer);
        order.setLocation(location);

        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));
    }
    public Order assignOrder(Long orderId, Long driverId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order Not Found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new RuntimeException("Driver Not Found"));

        if (!driver.isAvailable()) {
            throw new RuntimeException("Driver is not available");
        }

        order.setDriver(driver);
        order.setStatus(OrderStatus.ASSIGNED);

        driver.setAvailable(false);
        driverRepository.save(driver);

        statusNotifier.notifyStatusChange(
                "Order " + orderId + " ASSIGNED"
        );

        return orderRepository.save(order);
    }

    public Order pickupOrder(Long orderId) {

        Order order = getOrderById(orderId);

        if (order.getStatus() != OrderStatus.ASSIGNED) {
            throw new InvalidOrderStateException("Order must be ASSIGNED first");
        }

        order.setStatus(OrderStatus.PICKED_UP);

        statusNotifier.notifyStatusChange("Order " + orderId + " PICKED UP");

        return orderRepository.save(order);
    }

    public Order deliverOrder(Long orderId) {

        Order order = getOrderById(orderId);

        if (order.getStatus() != OrderStatus.PICKED_UP) {
            throw new RuntimeException(
                    "Order must be picked up before delivery"
            );
        }

        order.setStatus(OrderStatus.DELIVERED);

        Driver driver = order.getDriver();

        if (driver != null) {
            driver.setAvailable(true);
            driverRepository.save(driver);
        }

        statusNotifier.notifyStatusChange(
                "Order " + orderId + " DELIVERED"
        );

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order updateLocation(Long orderId,Long locationId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));

        order.setLocation(location);

        return orderRepository.save(order);
    }
}