package com.piotrkowalczykk.dormitory_management_app.customer.controller;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Post;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest addPostRequest){
        Post post = customerService.createPost(addPostRequest);
        return ResponseEntity.ok(post);
    }
}
