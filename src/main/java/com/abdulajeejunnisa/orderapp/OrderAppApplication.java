package com.abdulajeejunnisa.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderAppApplication.class, args);

        System.out.println();
        System.out.println("======================================");

        System.out.println(" ORDER TRACKING SYSTEM STARTED ");

        System.out.println(" Running on localhost:8080 ");

        System.out.println(" PostgreSQL Connected ");

        System.out.println("======================================");
    }
}