package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.PublicationServiceInterface;
import com.example.librarymanagement.interfaces.PurchaseServiceInterface;
import com.example.librarymanagement.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    @Autowired
    private DataSource dataSource;

    private final BookService bookService;
    public PurchaseService(BookService bookService) {
        this.bookService = bookService;
    }

    public final List<Purchase> purchaseList = new ArrayList<>();

    public void toList(){
        purchaseList.clear();
        String sql = "SELECT * FROM purchase";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                String vendor = rs.getString("vendor");
                Integer quantity = rs.getInt("quantity");
                Double perBookPrice = rs.getDouble("per_book_price");
                Double totalAmount = rs.getDouble("total_amount");
                LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();

                purchaseList.add(new Purchase(id, bookName, vendor, quantity, perBookPrice, totalAmount, purchaseDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Purchase> getALL() {
        toList();
        return purchaseList;
    }

    public Purchase save(Purchase purchase){
        String sql = "INSERT INTO purchase (book_name, vendor, quantity, per_book_price, total_amount, purchase_date) VALUES (?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, purchase.getBookName());
            statement.setString(2, purchase.getVendor());
            statement.setInt(3, purchase.getQuantity());
            statement.setDouble(4, purchase.getPerBookPrice());
            statement.setDouble(5, purchase.getTotalAmount());
            statement.setDate(6, Date.valueOf(purchase.getPurchaseDate()));

            statement.executeUpdate();

            bookService.updateQuantity(purchase.getBookName(), purchase.getQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchase;
    }

    public void delete(Integer id){
        String sql = "DELETE FROM purchase WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    public void update(Purchase purchase){
        delete(purchase.getId());
        save(purchase);
    }

}