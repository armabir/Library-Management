package com.example.librarymanagement;

import com.example.librarymanagement.model.Vendor;
import com.example.librarymanagement.service.VendorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VendorTest {
    @Autowired
    VendorService service;

    @Test
    public void saveTest(){
        Vendor vendor = new Vendor();
        vendor.setEmail("email DB");
        vendor.setName("Test");
        vendor.setPhone("phone");

//        service.save(vendor);
        service.update(vendor);

        System.out.println(service.getALL());
    }

}
