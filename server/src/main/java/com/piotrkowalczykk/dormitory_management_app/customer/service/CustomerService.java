package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.DormitoryDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.StudentDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Room;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;

import java.util.List;

public interface CustomerService {
    public List<StudentDTO> getAllStudents();
    public Article createArticle(ArticleRequest articleRequest);
    public void deleteArticle(Long articleId);
    public Article editArticle(Long articleId, ArticleRequest articleRequest);
    public List<Dormitory> getAllDormitories();
    public DormitoryDTO getDormitory(Long dormitoryId);
    public DormitoryDTO editDormitory(Long dormitoryId, DormitoryDTO dormitoryDTO);
    public void deleteDormitory(Long dormitoryId);
    public DormitoryDTO createDormitory(DormitoryDTO dormitoryDTO);
    public StudentDTO getStudent(Long studentId);
    public StudentDTO editStudent(Long studentId, StudentDTO studentDTO);
    public void deleteStudent(Long studentId);
    public List<Room> getAllRooms();
    public List<Room> getDormitoryRooms(Long dormitoryId);
}
