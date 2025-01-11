package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Post;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;

import java.util.List;

public interface CustomerService {
    public List<Student> getAllStudents();
    public Post createPost(PostRequest postRequest);
}
