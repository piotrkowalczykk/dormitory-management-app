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

    @PostMapping("/create-article")
    public ResponseEntity<Article> createArticle(@ModelAttribute ArticleRequest addArticleRequest){
        Article article = customerService.createArticle(addArticleRequest);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/delete-article/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId){
        customerService.deleteArticle(articleId);
        return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/siema")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("siema");
    }
}
