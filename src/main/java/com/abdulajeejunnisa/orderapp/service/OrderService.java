package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.model.Driver;
import com.abdulajeejunnisa.orderapp.model.Order;
import com.abdulajeejunnisa.orderapp.repository.OrderRepository;

public class OrderService {

    private  final OrderRepository repository = new OrderRepository();

    public void saveOrder(Order order) {
        repository.saveOrder(order);
    }

    public void assignDriver(Order order, Driver driver) {
        order.assignDriver(driver);
        repository.updateOrder(order);
        System.out.println("Driver assigned successfully");
    }

    public void pickupOrder(Order order) {
        order.pickup();
        repository.updateOrder(order);
        System.out.println("Order picked up successfully");
    }
    public void deliverOrder(Order order) {
        order.deliver();
        repository.updateOrder(order);
        System.out.println("Order delivered successfully");
    }
}
