package com.abdulajeejunnisa.orderapp;

import com.abdulajeejunnisa.orderapp.model.*;
import com.abdulajeejunnisa.orderapp.service.OrderService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Order {

    public static void main(String[] args) {

        OrderService orderService =
                new OrderService();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("===== CUSTOMER DETAILS =====");
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Customer Name: ");
            String customerName = sc.nextLine();

            System.out.print("Enter Customer Email: ");
            String customerEmail = sc.nextLine();

            System.out.print("Enter Customer Phone: ");
            String customerPhone = sc.nextLine();

            System.out.print("Enter Customer Address: ");
            String customerAddress = sc.nextLine();

            Customer customer = new Customer(
                    customerId,
                    customerName,
                    customerEmail,
                    customerPhone,
                    customerAddress
            );

            System.out.println("\n===== DRIVER DETAILS =====");

            System.out.print("Enter Driver ID: ");
            int driverId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Driver Name: ");
            String driverName = sc.nextLine();

            System.out.print("Enter Driver Email: ");
            String driverEmail = sc.nextLine();

            System.out.print("Enter Driver Phone: ");
            String driverPhone = sc.nextLine();

            System.out.print("Enter Vehicle Number: ");
            String vehicleNumber = sc.nextLine();

            Driver driver = new Driver(
                    driverId,
                    driverName,
                    driverEmail,
                    driverPhone,
                    vehicleNumber
            );

            System.out.println("\n===== LOCATION DETAILS =====");

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter State: ");
            String state = sc.nextLine();

            System.out.print("Enter Country: ");
            String country = sc.nextLine();

            System.out.print("Enter PinCode: ");
            String pinCode = sc.nextLine();

            Location location = new Location(
                    city,
                    state,
                    country,
                    pinCode
            );

            System.out.println("\n===== ORDER DETAILS =====");
            System.out.print("Enter Order ID: ");
            int orderId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Item Name: ");
            String itemName = sc.nextLine();
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            com.abdulajeejunnisa.orderapp.model.Order order = new com.abdulajeejunnisa.orderapp.model.Order(
                    orderId,
                    customer,
                    location,
                    amount,
                    quantity,
                    itemName
            );

            orderService.saveOrder(order);

            while (true) {

                System.out.println(
                        "\n===== ORDER MENU ====="
                );

                System.out.println("1. View Order");
                System.out.println("2. Assign Driver");
                System.out.println("3. Pickup Order");
                System.out.println("4. Deliver Order");
                System.out.println("5. View Status");
                System.out.println("6. Exit");

                System.out.print("Enter Choice: ");

                try {

                    int choice = sc.nextInt();

                    switch (choice) {

                        case 1 -> System.out.println(order);
                        case 2 -> orderService.assignDriver(order, driver);
                        case 3 -> orderService.pickupOrder(order);
                        case 4 -> orderService.deliverOrder(order);
                        case 5 -> System.out.println("Current Status: " + order.getStatus());
                        case 6 -> {
                            System.out.println("Application Closed");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid Choice");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please enter valid numbers");
                    sc.nextLine();
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Application Error: " + e.getMessage());
        }
    }
}