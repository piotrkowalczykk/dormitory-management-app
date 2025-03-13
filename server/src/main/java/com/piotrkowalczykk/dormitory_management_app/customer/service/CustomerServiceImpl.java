package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.DormitoryDTO;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.ArticleRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.DormitoryRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.StudentRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.utils.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final FileService fileService;
    private final DormitoryRepository dormitoryRepository;
    private final ArticleRepository articleRepository;
    private final StudentRepository studentRepository;
    private final AuthUserRepository authUserRepository;
    private final AcademyRepository academyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(FileService fileService, DormitoryRepository dormitoryRepository, ArticleRepository articleRepository, StudentRepository studentRepository, AuthUserRepository authUserRepository, AcademyRepository academyRepository) {
        this.fileService = fileService;
        this.dormitoryRepository = dormitoryRepository;
        this.articleRepository = articleRepository;
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
    public Article createArticle(ArticleRequest articleRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser customer = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("Customer not found"));

        String imagePath = null;
        if(articleRequest.getImage() != null && !articleRequest.getImage().isEmpty()){
            imagePath = fileService.saveFile(articleRequest.getImage(), "articles");
        }

        Article article = new Article();
        article.setAuthor(customer);
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setContent(articleRequest.getContent());
        article.setImage(imagePath);
        article.setVisibleInDormitories(dormitoryRepository.findAllById(articleRequest.getVisibleInDormitories()));

        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("Article not found"));

        if(!authentication.getName().equals(article.getAuthor().getEmail())){
            throw new AccessDeniedException("You are not the owner of this article");
        }

        articleRepository.delete(article);
        if(article.getImage() != null){
            fileService.deleteImage(article.getImage());
        }
    }

    @Override
    public Article editArticle(Long articleId, ArticleRequest articleRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("Article not found"));

        if(!authentication.getName().equals(article.getAuthor().getEmail())){
            throw new AccessDeniedException("You are not the owner of this article");
        }

        if(articleRequest.getImage() != null && !articleRequest.getImage().isEmpty() && articleRequest.getImage().getSize() > 0){
            if(article.getImage() != null && !article.getImage().isEmpty())
                fileService.deleteImage(article.getImage());

            String imagePath = fileService.saveFile(articleRequest.getImage(), "articles");
            article.setImage(imagePath);
        } else if (articleRequest.getImage() == null){
            if(article.getImage() != null  && !article.getImage().isEmpty()) {
                fileService.deleteImage(article.getImage());
                article.setImage("");
            }
        }

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDescription(articleRequest.getDescription());
        article.setLastModifiedDate(LocalDateTime.now());
        article.setVisibleInDormitories(dormitoryRepository.findAllById(articleRequest.getVisibleInDormitories()));
        return articleRepository.save(article);
    }

    @Override
    public List<Dormitory> getAllDormitories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dormitoryRepository.findAllByAcademyEmail(authentication.getName());
    }

    @Override
    public DormitoryDTO getDormitory(Long dormitoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Dormitory dormitory = dormitoryRepository.findById(dormitoryId)
                .orElseThrow(()-> new IllegalArgumentException("Dormitory not found"));

        if(!authentication.getName().equals(dormitory.getAcademy().getEmail())){
            throw new AccessDeniedException("You are not the owner of this dormitory");
        }

        return new DormitoryDTO(
                dormitory.getId(),
                dormitory.getName(),
                dormitory.getAddress(),
                dormitory.getPhone()
        );
    }

    @Override
    public DormitoryDTO editDormitory(Long dormitoryId, DormitoryDTO dormitoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Dormitory dormitory = dormitoryRepository.findById(dormitoryId)
                .orElseThrow(()-> new IllegalArgumentException("Dormitory not found"));

        if(!authentication.getName().equals(dormitory.getAcademy().getEmail())){
            throw new AccessDeniedException("You are not the owner of this dormitory");
        }

        dormitory.setName(dormitoryDTO.getName());
        dormitory.setAddress(dormitoryDTO.getAddress());
        dormitory.setPhone(dormitoryDTO.getPhone());

        dormitoryRepository.save(dormitory);
        return new DormitoryDTO(
                dormitory.getId(),
                dormitory.getName(),
                dormitory.getAddress(),
                dormitory.getPhone()
        );
    }

    @Override
    public void deleteDormitory(Long dormitoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Dormitory dormitory = dormitoryRepository.findById(dormitoryId)
                .orElseThrow(()-> new IllegalArgumentException("Dormitory not found"));

        if(!authentication.getName().equals(dormitory.getAcademy().getEmail())){
            throw new AccessDeniedException("You are not the owner of this dormitory");
        }

        dormitoryRepository.deleteById(dormitoryId);
    }

    @Override
    public DormitoryDTO createDormitory(DormitoryDTO dormitoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Academy academy = academyRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("Academy not found"));

        Dormitory dormitory = new Dormitory(
                dormitoryDTO.getName(),
                dormitoryDTO.getAddress(),
                dormitoryDTO.getPhone(),
                academy
        );

        dormitoryRepository.save(dormitory);
        return dormitoryDTO;
    }
}
