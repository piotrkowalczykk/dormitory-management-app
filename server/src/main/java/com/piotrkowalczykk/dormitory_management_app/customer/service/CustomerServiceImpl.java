package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.ArticleRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.StudentRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.utils.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final FileService fileService;
    private final ArticleRepository articleRepository;
    private final StudentRepository studentRepository;
    private final AuthUserRepository authUserRepository;
    private final AcademyRepository academyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(FileService fileService, ArticleRepository articleRepository, StudentRepository studentRepository, AuthUserRepository authUserRepository, AcademyRepository academyRepository) {
        this.fileService = fileService;
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

        String imagePath = fileService.saveFile(articleRequest.getImage(), "articles");

        Article article = new Article();
        article.setAuthor(customer);
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setContent(articleRequest.getContent());
        article.setImage(imagePath);
        article.setCreationDate(LocalDateTime.now());

        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long postId) {
        articleRepository.deleteById(postId);
    }

    @Override
    public Article editArticle(Long articleId, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("Article not found"));

        String imagePath = fileService.saveFile(articleRequest.getImage(), "articles");

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDescription(articleRequest.getDescription());
        article.setImage(imagePath);

        return articleRepository.save(article);
    }
}
