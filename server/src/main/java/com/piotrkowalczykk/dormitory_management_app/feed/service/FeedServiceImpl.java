package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.ArticleRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;
import com.piotrkowalczykk.dormitory_management_app.feed.repository.PostRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService{
    private final PostRepository postRepository;
    private final JsonWebToken jsonWebToken;
    private final ArticleRepository articleRepository;
    private final AcademyRepository academyRepository;
    private final AuthUserRepository authUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    public FeedServiceImpl(PostRepository postRepository, AcademyRepository academyRepository, AuthUserRepository authUserRepository, JsonWebToken jsonWebToken, ArticleRepository articleRepository) {
        this.postRepository = postRepository;
        this.academyRepository = academyRepository;
        this.authUserRepository = authUserRepository;
        this.jsonWebToken = jsonWebToken;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }

    @Override
    public UserDetailsResponse getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getName()));

        if(isAdmin){
            return new UserDetailsResponse(
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getDateOfBirth(),
                    null,
                    user.getRoles(),
                    user.getPosts()
            );
        }

        return new UserDetailsResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAcademy().getName(),
                user.getRoles(),
                user.getPosts()
        );
    }

    @Override
    public List<ArticleResponse> getAllArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        List<Article> articles = articleRepository.findAllByAuthorEmail(user.getAcademy().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Articles not found"));

        return articles.stream().map(article -> new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getImage(),
                article.getCreationDate().toLocalDate(),
                article.getContent()
        )).collect(Collectors.toList());
    }

    @Override
    public ArticleResponse getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getImage(),
                article.getCreationDate().toLocalDate(),
                article.getContent()
        );
    }

    @Override
    public List<Post> getAllPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        return postRepository.findAllPostsByAcademyId(user.getAcademy().getId());
    }

    @Override
    public Post createPost(PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        Post post = new Post(postRequest.getContent(), postRequest.getImage(), user);
        return postRepository.save(post);
    }

    @Override
    public Post editPost(PostRequest postRequest, Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));

        if(!authentication.getName().equals(post.getAuthor().getEmail())){
            throw new AccessDeniedException("You are not the owner of this post");
        }

        post.setContent(postRequest.getContent());
        post.setImage(postRequest.getImage());
        post.setLastModifiedDate(LocalDateTime.now());
        return postRepository.save(post);
    }
}
