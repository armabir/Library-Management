package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.AllotmentServiceInterface;
import com.example.librarymanagement.model.Allotment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllotmentService implements AllotmentServiceInterface {

    @Autowired
    private DataSource dataSource;

    private final BookService bookService;
    public AllotmentService(BookService bookService) {
        this.bookService = bookService;
    }

    public final List<Allotment> allotmentList = new ArrayList<>();

    public void toList(){
        allotmentList.clear();
        String sql = "SELECT * FROM allotment";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                String studentEmail = rs.getString("student_email");
                String subscriptionType = rs.getString("subscription_type");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();
                LocalDate allotmentDate = rs.getDate("allotment_date").toLocalDate();

                allotmentList.add(new Allotment(id, bookName, studentEmail, subscriptionType, startDate, endDate, allotmentDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Allotment> getALL() {
        toList();
        return allotmentList;
    }

    public Allotment save(Allotment allotment){
        String sql = "INSERT INTO allotment (book_name, student_email, subscription_type, start_date, end_date, allotment_date) VALUES (?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, allotment.getBookName());
            statement.setString(2, allotment.getStudentEmail());
            statement.setString(3, allotment.getSubscriptionType());
            statement.setDate(4, Date.valueOf(allotment.getStartDate()));
            statement.setDate(5, Date.valueOf(allotment.getEndDate()));
            statement.setDate(6, Date.valueOf(allotment.getAllotmentDate()));

            statement.executeUpdate();

            bookService.decreaseQuantity(allotment.getBookName(), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allotment;
    }

    public void delete(Integer id){
        String sql = "DELETE FROM allotment WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    public Allotment getById(Integer id){
        toList();
        return allotmentList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst().orElse(null);
    }
}