package ru.moysklad.repository;

import ru.moysklad.to.Product;
import ru.moysklad.util.ConnectionHelper;

import java.sql.*;
import java.time.LocalDate;

public class PostgreSqlStorage {

    private ConnectionHelper connectionHelper = new ConnectionHelper();

    public boolean createTable() {
        try (Connection conn = connectionHelper.getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE goods(" +
                    "id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, transaction VARCHAR, amount INTEGER, date DATE, price INTEGER)");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean dropTable() {
        try (Connection conn = connectionHelper.getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS goods");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean create(String name) {
        try (Connection conn = connectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO goods (name, transaction) values (?, ?)")) {
            if (nameExist(name)) {
                return false;
            }
            ps.setString(1, name);
            ps.setString(2, "initial");
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean update(Product product, String transaction) {
        try (Connection conn = connectionHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO goods (name, transaction, amount, date, price) VALUES (?, ?, ?, ?, ?)")) {
            if (!nameExist(product.getName())) {
                return false;
            }
            ps.setString(1, product.getName());
            ps.setString(2, transaction);
            ps.setInt(3, product.getAmount());
            ps.setDate(4, Date.valueOf(product.getLocalDate()));
            ps.setInt(5, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Integer select(String name, LocalDate localDate) {
        int profitamout = 0;
        int profit = 0;
        try (Connection conn = connectionHelper.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select amount, price, transaction from goods where name='" + name +
                     "' AND transaction IN ('purchase', 'demand') AND date<='" + Date.valueOf(localDate) + "' ORDER BY transaction, date")) {
            while (rs.next()) {
                if (rs.getString(3).equals("demand")) {
                    profitamout += rs.getInt(1);
                    profit += rs.getInt(1) * rs.getInt(2);
                } else {
                    int actualAmount = rs.getInt(1);
                    if (profitamout > actualAmount) {
                        profit -= actualAmount * rs.getInt(2);
                        profitamout -= actualAmount;
                    } else {
                        profit -= profitamout * rs.getInt(2);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return profit;
    }

    //verifies that the name exists
    private boolean nameExist(String name) throws SQLException {
        try (Connection conn = connectionHelper.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select name from goods where transaction='initial'")) {
            while (rs.next()) {
                if (rs.getString(1).equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}