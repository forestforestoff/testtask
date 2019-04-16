package ru.moysklad.service;

import ru.moysklad.repository.PostgreSqlStorage;
import ru.moysklad.to.Product;

import java.time.LocalDate;

public class ProductService {
    private PostgreSqlStorage postgreSqlStorage = new PostgreSqlStorage();

    public boolean initDB() {
        return postgreSqlStorage.createTable();
    }

    public boolean dropDB() {
        return postgreSqlStorage.dropTable();
    }

    public boolean create(String name) {
        return postgreSqlStorage.create(name);
    }

    public boolean purchase(Product product) {
        return postgreSqlStorage.update(product, "purchase");
    }

    public boolean demand(Product product) {
        return postgreSqlStorage.update(product, "demand");
    }

    public Integer salesreport(String name, LocalDate localDate) {
        return postgreSqlStorage.select(name, localDate);
    }
}