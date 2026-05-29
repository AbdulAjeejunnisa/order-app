package com.abdulajeejunnisa.orderapp.inpututil;

import java.util.Scanner;

public class InputUtil {

    public static int getIntInput(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextInt();
    }

    public static double getDoubleInput(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextDouble();
    }
}
