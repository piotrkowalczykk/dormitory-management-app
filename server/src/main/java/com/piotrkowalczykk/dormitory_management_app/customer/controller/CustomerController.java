package com.piotrkowalczykk.dormitory_management_app.customer.controller;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.DormitoryDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.RoomDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.StudentDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Room;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.service.CustomerService;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.PublicUserDTO;
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
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(customerService.getAllStudents());
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(customerService.getStudent(studentId));
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<StudentDTO> editStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(customerService.editStudent(studentId, studentDTO));
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
        customerService.deleteStudent(studentId);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(customerService.createStudent(studentDTO));
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@ModelAttribute ArticleRequest addArticleRequest){
        Article article = customerService.createArticle(addArticleRequest);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId){
        customerService.deleteArticle(articleId);
        return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<Article> editArticle(@PathVariable Long articleId, @ModelAttribute ArticleRequest articleRequest){
       Article article = customerService.editArticle(articleId, articleRequest);
       return ResponseEntity.ok(article);
    }

    @GetMapping("/dormitories")
    public ResponseEntity<List<Dormitory>> getAllDormitories(){
        List<Dormitory> listOfDormitories = customerService.getAllDormitories();
        return ResponseEntity.ok(listOfDormitories);
    }

    @GetMapping("/dormitories/{dormitoryId}")
    public ResponseEntity<DormitoryDTO> getDormitory(@PathVariable Long dormitoryId){
        return ResponseEntity.ok(customerService.getDormitory(dormitoryId));
    }

    @PutMapping("/dormitories/{dormitoryId}")
    public ResponseEntity<DormitoryDTO> editDormitory(@PathVariable Long dormitoryId, @RequestBody DormitoryDTO dormitoryDTO){
        return ResponseEntity.ok(customerService.editDormitory(dormitoryId, dormitoryDTO));
    }

    @PostMapping("/dormitories")
    public ResponseEntity<DormitoryDTO> createDormitory(@RequestBody DormitoryDTO dormitoryDTO){
        return ResponseEntity.ok(customerService.createDormitory(dormitoryDTO));
    }

    @DeleteMapping("/dormitories/{dormitoryId}")
    public ResponseEntity<String> deleteDormitory(@PathVariable Long dormitoryId){
        customerService.deleteDormitory(dormitoryId);
        return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/dormitory/{dormitoryId}/rooms")
    public ResponseEntity<List<Room>> getDormitoryRooms(@PathVariable Long dormitoryId){
        return ResponseEntity.ok(customerService.getDormitoryRooms(dormitoryId));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(customerService.getAllRooms());
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(customerService.getRoom(roomId));
    }

    @PutMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDTO> editRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok(customerService.editRoom(roomId, roomDTO));
    }
    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId){
        customerService.deleteRoom(roomId);
        return ResponseEntity.ok("Room deleted successfully");
    }

}
