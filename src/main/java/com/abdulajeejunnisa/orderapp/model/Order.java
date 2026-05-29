package com.abdulajeejunnisa.orderapp.model;

public class Order {

    private final int orderId;
    private final Customer customer;
    private Driver driver;
    private final Location location;
    private final double amount;
    private final int quantity;
    private final String itemName;
    private OrderStatus status;

    public Order(
            int orderId,
            Customer customer,
            Location location,
            double amount,
            int quantity,
            String itemName
    ) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        this.orderId = orderId;
        this.customer = customer;
        this.location = location;
        this.amount = amount;
        this.quantity = quantity;
        this.itemName = itemName;
        this.status = OrderStatus.CREATED;
    }

    public void assignDriver(Driver driver) {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Driver can only be assigned when order is CREATED");
        }
        this.driver = driver;
        this.status = OrderStatus.ASSIGNED;
    }

    public void pickup() {
        if (status != OrderStatus.ASSIGNED) {
            throw new IllegalStateException("Order can only be picked up when ASSIGNED");
        }
        status = OrderStatus.PICKED_UP;
    }
    public void deliver() {
        if (status != OrderStatus.PICKED_UP) {
            throw new IllegalStateException("Order cannot be delivered before pickup");
        }
        status = OrderStatus.DELIVERED;
    }
    public int getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Driver getDriver() {
        return driver;
    }
    public Location getLocation() {
        return location;
    }
    public double getAmount() {
        return amount;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getItemName() {
        return itemName;
    }
    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "\n========== ORDER DETAILS ==========\n" +
                "Order ID     : " + orderId +
                "\nCustomer     : " + customer.getName() +
                "\nItem         : " + itemName +
                "\nQuantity     : " + quantity +
                "\nAmount       : ₹" + amount +
                "\nLocation     : " + location +
                "\nStatus       : " + status +
                "\nDriver       : " +
                (driver != null
                        ? driver.getName()
                        : "Not Assigned") +
                "\n===================================";
    }
}