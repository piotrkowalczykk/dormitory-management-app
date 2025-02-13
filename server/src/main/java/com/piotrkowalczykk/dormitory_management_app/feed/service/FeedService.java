package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.PostRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;

import java.util.List;

public interface FeedService {
    public List<Academy> getAllAcademies();
    public UserDetailsResponse getUserDetails();
    public List<ArticleResponse> getAllArticles();
    public ArticleResponse getArticle(Long articleId);
    public List<Post> getAllPosts();
    public Post createPost(PostRequest postRequest);
    public Post editPost(PostRequest postRequest, Long postId);
    public void deletePost(Long postId);
    public Post likePost(Long postId);
}
