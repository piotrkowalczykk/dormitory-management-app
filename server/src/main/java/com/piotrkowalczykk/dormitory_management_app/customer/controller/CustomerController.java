package com.piotrkowalczykk.dormitory_management_app.customer.controller;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> listOfStudents = customerService.getAllStudents();
        return ResponseEntity.ok(listOfStudents);
    }
}
