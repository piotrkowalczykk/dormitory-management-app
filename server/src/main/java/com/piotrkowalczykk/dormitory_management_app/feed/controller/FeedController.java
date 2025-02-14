package com.piotrkowalczykk.dormitory_management_app.feed.controller;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.CommentRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Comment;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;
import com.piotrkowalczykk.dormitory_management_app.feed.service.FeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("/me")
    public UserDetailsResponse getUserDetails(){
        return feedService.getUserDetails();
    }

    @GetMapping("/academies")
    public ResponseEntity<List<Academy>> getAllAcademies(){
       List<Academy> listOfAcademies = feedService.getAllAcademies();
       return ResponseEntity.ok(listOfAcademies);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        return ResponseEntity.ok(feedService.getAllArticles());
    }


    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long articleId){
        return ResponseEntity.ok(feedService.getArticle(articleId));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> listOfPosts = feedService.getAllPosts();
        return ResponseEntity.ok(listOfPosts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest){
        Post post = feedService.createPost(postRequest);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> editPost(@RequestBody PostRequest postRequest, @PathVariable Long postId){
        Post post = feedService.editPost(postRequest, postId);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        feedService.deletePost(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long postId){
        Post post = feedService.likePost(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest){
        Comment comment = feedService.addComment(postId, commentRequest);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId){
        List<Comment> listOfComments = feedService.getComments(postId);
        return ResponseEntity.ok(listOfComments);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> editComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest){
        Comment comment = feedService.editComment(commentId, commentRequest);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        feedService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
