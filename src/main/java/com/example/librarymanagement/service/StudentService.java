package com.example.librarymanagement.service;

import com.example.librarymanagement.interfaces.StudentServiceInterface;
import com.example.librarymanagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {

    @Autowired
    private DataSource dataSource;
    public final List<Student> studentList = new ArrayList<>();

    public void toList(){
        studentList.clear();
        String sql = "SELECT * FROM student";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                LocalDate registrationDate = rs.getDate("registration_date").toLocalDate();

                studentList.add(new Student(name, email, phone, registrationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getALL() {
        toList();
        return studentList;
    }

    public Student save(Student student){
        if(getByEmail(student.getEmail()) != null){
            System.out.println("Student already Exist");
            return null;
        }

        String sql = "INSERT INTO student (name, email, phone, registration_date) VALUES (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setDate(4, Date.valueOf(student.getRegistrationDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student getByEmail(String email){
        toList();
        return studentList.stream()
                .filter(s -> s.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    public boolean exists(String email){
        toList();
        return studentList.stream()
                .anyMatch(s -> s.getEmail().equals(email));
    }

    public void delete(String email){
        String sql = "DELETE FROM student WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        toList();
    }

    public void update(Student student){
        delete(student.getEmail());
        save(student);
    }
}