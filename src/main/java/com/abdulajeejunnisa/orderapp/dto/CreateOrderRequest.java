package com.abdulajeejunnisa.orderapp.dto;

public class CreateOrderRequest {

    private String itemName;
    private int quantity;
    private double amount;
    private Long customerId;
    private Long locationId;

    public CreateOrderRequest() {
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(
            Long customerId) {

        this.customerId = customerId;
    }
    public Long getLocationId() {
        return locationId;
    }
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}