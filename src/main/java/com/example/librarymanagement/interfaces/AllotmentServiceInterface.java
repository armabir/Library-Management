package com.example.librarymanagement.interfaces;

import com.example.librarymanagement.model.Allotment;
import com.example.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AllotmentServiceInterface {
    void toList();
    List<Allotment> getALL ();
    Allotment save(Allotment allotment);
    void delete (Integer id);
    Allotment getById(Integer id);
}