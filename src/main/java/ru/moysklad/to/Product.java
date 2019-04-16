package ru.moysklad.to;

import java.time.LocalDate;

public class Product {
    private final String name;
    private int amount;
    private int price;
    private LocalDate localDate;

    public Product(String name, LocalDate localDate) {
        this.name = name;
        this.localDate = localDate;
    }

    public Product(String name, int amount, int price, LocalDate localDate) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.localDate = localDate;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

}