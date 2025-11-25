package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.VendorServiceInterface;
import com.example.librarymanagement.model.Vendor;
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
public class VendorService implements VendorServiceInterface {

    @Autowired
    private DataSource dataSource;

    public final List<Vendor> vendorList = new ArrayList<>();

    public void toList(){
        vendorList.clear();
        String sql = "SELECT * FROM vendor";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                vendorList.add(new Vendor(name, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vendor> getALL() {
        toList();
        return vendorList;
    }

    public Vendor save(Vendor vendor){
        if(getByName(vendor.getName()) != null){
            System.out.println("Vendor already Exist");
            return null;
        }

        String sql = "INSERT INTO vendor (name, email, phone) VALUES (?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vendor.getName());
            statement.setString(2, vendor.getEmail());
            statement.setString(3, vendor.getPhone());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendor;
    }

    public Vendor getByName(String name){
        toList();
        return vendorList.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst().orElse(null);
    }

    public boolean exists(String name){
        toList();
        return vendorList.stream()
                .anyMatch(v -> v.getName().equals(name));
    }

    public void delete(String name){
        String sql = "DELETE FROM vendor WHERE name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    public void update(Vendor vendor){
        delete(vendor.getName());
        save(vendor);
    }
}