package com.piotrkowalczykk.dormitory_management_app.feed.controller;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Post;
import com.piotrkowalczykk.dormitory_management_app.customer.repository.PostRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.SelectAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;
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
    public UserDetailsResponse getUserDetails(@RequestHeader("Authorization") String authHeader){
        return feedService.getUserDetails(authHeader);
    }

    @GetMapping("/academies")
    public ResponseEntity<List<Academy>> getAllAcademies(){
       List<Academy> listOfAcademies = feedService.getAllAcademies();
       return ResponseEntity.ok(listOfAcademies);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(feedService.getAllPosts());
    }
}
