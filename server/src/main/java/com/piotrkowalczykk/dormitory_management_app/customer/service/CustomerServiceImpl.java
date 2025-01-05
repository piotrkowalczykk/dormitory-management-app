package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.StudentRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final StudentRepository studentRepository;
    private final AuthUserRepository authUserRepository;
    private final AcademyRepository academyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(StudentRepository studentRepository, AuthUserRepository authUserRepository, AcademyRepository academyRepository) {
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
}
