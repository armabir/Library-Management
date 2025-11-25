package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.PublicationServiceInterface;
import com.example.librarymanagement.model.Publication;
import com.example.librarymanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PublicationService implements PublicationServiceInterface {

    @Autowired
    private DataSource dataSource;

    public final List<Publication> publicationList = new ArrayList<>();

    public void toList(){
        publicationList.clear();
        String sql = "SELECT * FROM publication";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String description = rs.getString("description");

                publicationList.add(new Publication(name, address, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Publication> getALL() {
        toList();
        return publicationList;
    }

    public Publication save(Publication publication){
        if(getByName(publication.getName()) != null){
            System.out.println("Publication already Exist");
            return null;
        }

        String sql = "INSERT INTO publication (name, address, description) VALUES (?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, publication.getName());
                statement.setString(2, publication.getAddress());
                statement.setString(3, publication.getDescription());

                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publication;
    }

    public Publication getByName(String name){
        toList();
        return publicationList.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
    }

    public void delete(String name){
        String sql = "DELETE FROM publication WHERE name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Publication publication){
        delete(publication.getName());
        save(publication);
    }
}
