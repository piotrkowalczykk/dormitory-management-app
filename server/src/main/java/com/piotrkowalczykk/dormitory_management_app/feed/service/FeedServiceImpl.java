package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.ArticleRepository;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.StudentRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.*;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Comment;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;
import com.piotrkowalczykk.dormitory_management_app.feed.repository.CommentRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.repository.PostRepository;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.JsonWebToken;
import com.piotrkowalczykk.dormitory_management_app.utils.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService{
    private final StudentRepository studentRepository;
    private final FileService fileService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final JsonWebToken jsonWebToken;
    private final ArticleRepository articleRepository;
    private final AcademyRepository academyRepository;
    private final AuthUserRepository authUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    public FeedServiceImpl(StudentRepository studentRepository, FileService fileService, CommentRepository commentRepository, PostRepository postRepository, AcademyRepository academyRepository, AuthUserRepository authUserRepository, JsonWebToken jsonWebToken, ArticleRepository articleRepository) {
        this.studentRepository = studentRepository;
        this.fileService = fileService;
        this.commentRepository = commentRepository;
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
                    user.getId(),
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
                user.getId(),
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

        List<Article> articles = new ArrayList<>();

        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))){
            articles = articleRepository.findAllByAuthorEmail(user.getAcademy().getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Articles not found"));
        } else {
            Student student = studentRepository.findByEmail(authentication.getName())
                    .orElseThrow(()-> new IllegalArgumentException("Student not found"));
            articles = articleRepository.findAllByAuthorEmailAndDormitoryId(user.getAcademy().getEmail(), student.getDormitory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Articles not found"));
        }

        return articles.stream().map(article -> new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getImage(),
                article.getCreationDate(),
                article.getContent(),
                article.getLastModifiedDate(),
                article.getVisibleInDormitories()
        )).collect(Collectors.toList());
    }

    @Override
    public ArticleResponse getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getImage(),
                article.getCreationDate(),
                article.getContent(),
                article.getLastModifiedDate(),
                article.getVisibleInDormitories()
        );
    }

    @Override
    public List<PostDTO> getAllPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        List<Post> listOfPosts =  postRepository.findAllPostsByAcademyId(user.getAcademy().getId());

        return listOfPosts.stream().map(post -> new PostDTO(
                post.getId(),
                post.getContent(),
                post.getImage(),
                new PublicUserDTO(
                        post.getAuthor().getId(),
                        post.getAuthor().getFirstName(),
                        post.getAuthor().getLastName(),
                        "avatar"),
                post.getLikes().stream().map(likedUser -> new PublicUserDTO(
                        likedUser.getId(),
                        likedUser.getFirstName(),
                        likedUser.getLastName(),
                        "avatar"
                )).collect(Collectors.toSet()),
                post.getComments().stream().map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getContent(),
                        new PublicUserDTO(
                                comment.getAuthor().getId(),
                                comment.getAuthor().getFirstName(),
                                comment.getAuthor().getLastName(),
                                "avatar"
                        ),
                        comment.getCreationDate(),
                        comment.getLastModifiedDate()
                )).collect(Collectors.toList()),
                post.getCreationDate(),
                post.getLastModifiedDate()
        )).collect(Collectors.toList());
    }

    @Override
    public Post createPost(PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        String imagePath = null;
        if(postRequest.getImage() != null && !postRequest.getImage().isEmpty()){
            imagePath = fileService.saveFile(postRequest.getImage(), "posts");
        }

        Post post = new Post(postRequest.getContent(), imagePath, user);
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

        String imagePath = post.getImage();

        if(postRequest.getImage() != null && !postRequest.getImage().isEmpty()){
            imagePath = fileService.saveFile(postRequest.getImage(), "posts");
        }

        post.setContent(postRequest.getContent());
        post.setImage(imagePath);
        post.setLastModifiedDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));

        if(!authentication.getName().equals(post.getAuthor().getEmail())){
            throw new AccessDeniedException("You are not the owner of this post");
        }

        fileService.deleteImage(post.getImage());
        postRepository.delete(post);
    }

    @Override
    public PostDTO likePost(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        if(!user.getAcademy().equals(post.getAuthor().getAcademy())){
            throw new AccessDeniedException("This post was created by a user from a different academy.");
        }

        if(post.getLikes().contains(user)){
            post.getLikes().remove(user);
        } else {
            post.getLikes().add(user);
        }

        postRepository.save(post);

        return new PostDTO(
                post.getId(),
                post.getContent(),
                post.getImage(),
                new PublicUserDTO(
                        post.getAuthor().getId(),
                        post.getAuthor().getFirstName(),
                        post.getAuthor().getLastName(),
                        "avatar"),
                post.getLikes().stream().map(like -> new PublicUserDTO(
                        like.getId(),
                        like.getFirstName(),
                        like.getLastName(),
                        "avatar"
                )).collect(Collectors.toSet()),
                post.getComments().stream().map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getContent(),
                        new PublicUserDTO(
                                comment.getAuthor().getId(),
                                comment.getAuthor().getFirstName(),
                                comment.getAuthor().getLastName(),
                                "avatar"),
                        comment.getCreationDate(),
                        comment.getLastModifiedDate()
                )).collect(Collectors.toList()),
                post.getCreationDate(),
                post.getLastModifiedDate()
        );
    }

    @Override
    public Comment addComment(Long postId, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        Comment comment = new Comment(commentRequest.getContent(), post, user);
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> getComments(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        if(!user.getAcademy().equals(post.getAuthor().getAcademy())){
            throw new AccessDeniedException("This post was created by a user from a different academy.");
        }
        return post.getComments().stream().map(comment -> new CommentDTO(
                comment.getId(),
                comment.getContent(),
                new PublicUserDTO(
                        comment.getAuthor().getId(),
                        comment.getAuthor().getFirstName(),
                        comment.getAuthor().getLastName(),
                        "avatar"),
                comment.getCreationDate(),
                comment.getLastModifiedDate()
        )).collect(Collectors.toList());
    }

    @Override
    public Comment editComment(Long commentId, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("Comment not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        if(!comment.getAuthor().equals(user)){
            throw new AccessDeniedException("You are not the owner of this comment");
        }

        comment.setContent(commentRequest.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!comment.getAuthor().equals(user)) {
            throw new AccessDeniedException("You are not the owner of this comment");
        }

        commentRepository.delete(comment);
    }

    @Override
    public Set<PublicUserDTO> getPostLikes(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post not found"));
        AuthUser user = authUserRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        if(!user.getAcademy().equals(post.getAuthor().getAcademy())){
            throw new AccessDeniedException("This post was created by a user from a different academy.");
        }

        return post.getLikes().stream().map(authUser -> new PublicUserDTO(
                authUser.getId(),
                authUser.getFirstName(),
                authUser.getLastName(),
                "avatar"
        )).collect(Collectors.toSet());
    }
}
