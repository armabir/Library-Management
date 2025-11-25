package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.SubscriptionServiceInterface;
import com.example.librarymanagement.model.Subscription;
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
public class SubscriptionService implements SubscriptionServiceInterface {

    @Autowired
    private DataSource dataSource;

    public final List<Subscription> subscriptionList = new ArrayList<>();

    public void toList(){
        subscriptionList.clear();
        String sql = "SELECT * FROM subscription";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("title");
                Double amount = rs.getDouble("amount");
                Integer numberOfDays = rs.getInt("number_of_days");

                subscriptionList.add(new Subscription(title, amount, numberOfDays));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subscription> getALL() {
        toList();
        return subscriptionList;
    }

    public Subscription save(Subscription subscription){
        if(getByTitle(subscription.getTitle()) != null){
            System.out.println("Subscription already Exist");
            return null;
        }

        String sql = "INSERT INTO subscription (title, amount, number_of_days) VALUES (?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subscription.getTitle());
            statement.setDouble(2, subscription.getAmount());
            statement.setInt(3, subscription.getNumberOfDays());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscription;
    }

    public Subscription getByTitle(String title){
        toList();
        return subscriptionList.stream()
                .filter(s -> s.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public boolean exists(String title){
        toList();
        return subscriptionList.stream()
                .anyMatch(s -> s.getTitle().equals(title));
    }

    public void delete(String title){
        String sql = "DELETE FROM subscription WHERE title = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Subscription subscription){
        delete(subscription.getTitle());
        save(subscription);
    }
}