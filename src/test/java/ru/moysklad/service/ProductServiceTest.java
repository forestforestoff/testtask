package ru.moysklad.service;

import org.junit.Assert;
import org.junit.Test;
import ru.moysklad.to.Product;

import java.time.LocalDate;

public class ProductServiceTest {
    private ProductService productService = new ProductService();

    {
        productService.dropDB();
        productService.initDB();
        productService.create("iphone");
        productService.purchase(new Product("iphone", 1, 1000, LocalDate.of(2017, 1, 1)));
        productService.purchase(new Product("iphone", 2, 2000, LocalDate.of(2017, 2, 1)));
        productService.demand(new Product("iphone", 2, 5000, LocalDate.of(2017, 3, 1)));
    }

    @Test
    public void create() {
        Assert.assertTrue(productService.create("nokia"));
        Assert.assertFalse(productService.create("nokia"));
    }

    @Test
    public void purchase() {
        Assert.assertTrue(productService.purchase(new Product("iphone", 3, 1500, LocalDate.of(2017, 2, 10))));
        Assert.assertFalse(productService.purchase(new Product("samsung", 3, 1500, LocalDate.of(2017, 1, 1))));
    }

    @Test
    public void demand() {
        Assert.assertTrue(productService.demand(new Product("iphone", 3, 5000, LocalDate.of(2017, 2, 11))));
        Assert.assertFalse(productService.demand(new Product("huawei", 3, 1500, LocalDate.of(2017, 1, 1))));
    }

    @Test
    public void salesreport() {
        Assert.assertEquals(0, (int) productService.salesreport("iphone", LocalDate.of(2017, 2, 1)));
        Assert.assertEquals(7000, (int) productService.salesreport("iphone", LocalDate.of(2017, 3, 1)));
    }
}