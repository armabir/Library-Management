package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.BookServiceInterface;
import com.example.librarymanagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    private DataSource dataSource;

    public final List<Book> bookList = new ArrayList<>();

    public void toList(){
        bookList.clear();
        String sql = "SELECT * FROM book";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String bookName = rs.getString("book_name");
                String bookImage = rs.getString("book_image");
                String authorName = rs.getString("author_name");
                String publisherName = rs.getString("publisher_name");
                int availableQuantity = rs.getInt("available_quantity");

                bookList.add(new Book(bookName, bookImage, authorName, publisherName, availableQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getALL() {
        toList();
        return bookList;
    }

    public Book save(Book book){
        if(getByBookName(book.getBookName()) != null){
            System.out.println("Book already Exist");
            return null;
        }

        String sql = "INSERT INTO book (book_name, book_image, author_name, publisher_name, available_quantity) VALUES (?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getBookImage());
            statement.setString(3, book.getAuthorName());
            statement.setString(4, book.getPublisherName());
            statement.setInt(5, book.getAvailableQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public Book getByBookName(String bookName){
        toList();
        return bookList.stream()
                .filter(b -> b.getBookName().equals(bookName))
                .findFirst().orElse(null);
    }

    public void delete(String bookName){
        String sql = "DELETE FROM book WHERE book_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    public void update(Book book){
        String sql = "UPDATE book SET book_image = ?, author_name = ?, publisher_name = ? WHERE book_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getBookImage());
            statement.setString(2, book.getAuthorName());
            statement.setString(3, book.getPublisherName());
            statement.setString(4, book.getBookName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    // used for purchase
    public void updateQuantity(String bookName, Integer newQuantity){
        String sql = "UPDATE book SET available_quantity = available_quantity + ? WHERE book_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setString(2, bookName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    // used for allotment
    public void decreaseQuantity(String bookName, Integer quantity){
        String sql = "UPDATE book SET available_quantity = available_quantity - ? WHERE book_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setString(2, bookName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    // used for return
    public void increaseQuantity(String bookName, Integer quantity){
        String sql = "UPDATE book SET available_quantity = available_quantity + ? WHERE book_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setString(2, bookName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }
}