package com.piotrkowalczykk.dormitory_management_app.customer.controller;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Article> createPost(@RequestBody ArticleRequest addArticleRequest){
        Article article = customerService.createArticle(addArticleRequest);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        customerService.deleteArticle(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
