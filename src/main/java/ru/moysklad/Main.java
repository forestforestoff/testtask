package ru.moysklad;

import ru.moysklad.to.Product;
import ru.moysklad.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static ru.moysklad.util.ParseCommandUtil.validateParams;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ProductService productService = new ProductService();
        Product product;
        while (true) {
            String[] cs = reader.readLine().trim().split(" ");
            switch (cs[0]) {
                case "INITDB" :
                    System.out.println(productService.initDB() ? "OK" : "DB CREATE ERROR");
                    break;
                case "DROPDB":
                    System.out.println(productService.dropDB() ? "OK" : "DB DROP ERROR");
                    break;
                case "NEWPRODUCT":
                    if (cs.length != 2) {
                        System.out.println("ERROR");
                        break;
                    }
                    System.out.println(productService.create(cs[1]) ? "OK" : "ERROR");
                    break;
                case "PURCHASE":
                    product = validateParams(cs);
                    System.out.println(product != null && productService.purchase(product) ? "OK" : "ERROR");
                    break;
                case "DEMAND":
                    product = validateParams(cs);
                    System.out.println(product != null && productService.demand(product) ? "OK" : "ERROR");
                    break;
                case "SALESREPORT":
                    product = validateParams(cs);
                    if (product == null) {
                        System.out.println("ERROR");
                        break;
                    }
                    Integer profit = productService.salesreport(product.getName(), product.getLocalDate());
                    System.out.println(profit != null ? profit : "ERROR");
                    break;
                case "EXIT":
                    return;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }
}