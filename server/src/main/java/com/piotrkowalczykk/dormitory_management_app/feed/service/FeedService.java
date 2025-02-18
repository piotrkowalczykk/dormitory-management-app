package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.*;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Comment;
import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;

import java.util.List;
import java.util.Set;

public interface FeedService {
    public List<Academy> getAllAcademies();
    public UserDetailsResponse getUserDetails();
    public List<ArticleResponse> getAllArticles();
    public ArticleResponse getArticle(Long articleId);
    public List<PostDTO> getAllPosts();
    public Post createPost(PostRequest postRequest);
    public Post editPost(PostRequest postRequest, Long postId);
    public void deletePost(Long postId);
    public PostDTO likePost(Long postId);
    public Comment addComment(Long postId, CommentRequest commentRequest);
    public List<CommentDTO> getComments(Long postId);
    public Comment editComment(Long commentId, CommentRequest commentRequest);
    public void deleteComment(Long commentId);
    public Set<PublicUserDTO> getPostLikes(Long postId);

}
