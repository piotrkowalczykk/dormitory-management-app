package com.piotrkowalczykk.dormitory_management_app.admin.controller;

import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyResponse;
import com.piotrkowalczykk.dormitory_management_app.admin.service.AcademyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AcademyService academyService;

    public AdminController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @PostMapping("/add-academy")
    public AddAcademyResponse addAcademy(@RequestBody @Valid AddAcademyRequest addAcademyRequest){
        return academyService.addAcademy(addAcademyRequest);
    }

}
