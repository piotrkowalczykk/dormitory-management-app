package com.piotrkowalczykk.dormitory_management_app.admin.controller;

import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyResponse;
import com.piotrkowalczykk.dormitory_management_app.admin.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add-academy")
    public AddAcademyResponse addAcademy(@RequestBody @Valid AddAcademyRequest addAcademyRequest){
        return adminService.addAcademy(addAcademyRequest);
    }

}
