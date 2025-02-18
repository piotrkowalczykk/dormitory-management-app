package com.piotrkowalczykk.dormitory_management_app.feed.dto;

import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;
import com.piotrkowalczykk.dormitory_management_app.security.model.Gender;
import com.piotrkowalczykk.dormitory_management_app.security.model.Role;

import java.time.LocalDate;
import java.util.List;

public class UserDetailsResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String academyName;
    private List<Role> roles;
    private List<Post> posts;

    public UserDetailsResponse(Long id, String email, String firstName, String lastName, Gender gender, LocalDate dateOfBirth, String academyName, List<Role> roles, List<Post> posts) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.academyName = academyName;
        this.roles = roles;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
