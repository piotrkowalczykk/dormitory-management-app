package com.piotrkowalczykk.dormitory_management_app.admin.service;

import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyResponse;
import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcademyServiceImpl implements AcademyService{

    private final AcademyRepository academyRepository;

    public AcademyServiceImpl(AcademyRepository academyRepository){
        this.academyRepository = academyRepository;
    }

    @Override
    public Optional<Academy> findByName(String name) {
        return academyRepository.findByName(name);
    }

    @Override
    public AddAcademyResponse addAcademy(AddAcademyRequest addAcademyRequest) {

        Optional<Academy> academy = academyRepository.findByName(addAcademyRequest.getName());
        if(academy.isPresent()) {
            return new AddAcademyResponse("name: academy already exists");
        }

        Academy newAcademy = new Academy();
        newAcademy.setAddress(addAcademyRequest.getAddress());
        newAcademy.setCity(addAcademyRequest.getCity());
        newAcademy.setLogo(addAcademyRequest.getLogo());
        newAcademy.setCountry(addAcademyRequest.getCountry());
        newAcademy.setDescription(addAcademyRequest.getDescription());
        newAcademy.setPhone(addAcademyRequest.getPhone());
        newAcademy.setPostalCode(addAcademyRequest.getPostalCode());
        newAcademy.setName(addAcademyRequest.getName());
        newAcademy.setWebsite(addAcademyRequest.getWebsite());
        newAcademy.setEmail(addAcademyRequest.getEmail());
        academyRepository.save(newAcademy);

        return new AddAcademyResponse("Academy added successfully");
    }
}
