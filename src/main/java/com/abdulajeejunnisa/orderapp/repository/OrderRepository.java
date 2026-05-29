package com.abdulajeejunnisa.orderapp.repository;

import com.abdulajeejunnisa.orderapp.config.DatabaseConnection;
import com.abdulajeejunnisa.orderapp.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {

    public void saveOrder(Order order) {

        String sql = """
                INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setInt(1, order.getOrderId());
            statement.setString(2, order.getCustomer().getName());
            statement.setString(3, order.getDriver() != null ? order.getDriver().getName() : "Not Assigned");
            statement.setString(4, order.getLocation().toString());
            statement.setDouble(5, order.getAmount());
            statement.setInt(6, order.getQuantity());
            statement.setString(7, order.getItemName());
            statement.setString(8, order.getStatus().name());
            statement.executeUpdate();
            System.out.println("Order saved successfully");
        } catch (SQLException e) {

            System.out.println("Database Error : " + e.getMessage());
        }
    }

    public void updateOrder(Order order) {

        String sql = """
            UPDATE orders
            SET
                driver_name = ?,
                status = ?
            WHERE order_id = ?
            """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    order.getDriver().getName()
            );

            statement.setString(
                    2,
                    order.getStatus().name()
            );

            statement.setInt(
                    3,
                    order.getOrderId()
            );

            int rows = statement.executeUpdate();

            System.out.println(
                    "Rows Updated : " + rows
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}