package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Post;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.PostRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.StudentRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final PostRepository postRepository;
    private final StudentRepository studentRepository;
    private final AuthUserRepository authUserRepository;
    private final AcademyRepository academyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(PostRepository postRepository, StudentRepository studentRepository, AuthUserRepository authUserRepository, AcademyRepository academyRepository) {
        this.postRepository = postRepository;
        this.studentRepository = studentRepository;
        this.authUserRepository = authUserRepository;
        this.academyRepository = academyRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedCustomerName = authentication.getName();

        AuthUser user = authUserRepository.findByEmail(loggedCustomerName)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Academy academy = user.getAcademy();

        return studentRepository.getAllStudentsByAcademyId(academy.getId())
                .orElseThrow(()-> new IllegalArgumentException("Academy not found"));
    }

    @Override
    public Post createPost(PostRequest postRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser customer = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("Customer not found"));

        Post post = new Post();
        post.setAuthor(customer);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setImage(postRequest.getImage());
        post.setCreationDate(LocalDateTime.now());

        return postRepository.save(post);
    }
}
