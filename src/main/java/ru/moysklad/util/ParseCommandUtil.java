package ru.moysklad.util;

import ru.moysklad.to.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseCommandUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static Product validateParams(String[] cs) {
        Product product = null;
        int amount;
        int price;
        LocalDate localDate;
        switch (cs.length) {
            case 3:
                try {
                    localDate = LocalDate.parse(cs[2], FORMATTER);
                } catch (Exception e) {
                    break;
                }
                product = new Product(cs[1], localDate);
                break;
            case 5:
                try {
                    amount = Integer.parseInt(cs[2]);
                    price = Integer.parseInt(cs[3]);
                    localDate = LocalDate.parse(cs[4], FORMATTER);
                } catch (Exception e) {
                    break;
                }
                if (amount <= 0 || price <= 0) {
                    break;
                }
                product = new Product(cs[1], amount, price, localDate);
                break;
        }
        return product;
    }
}