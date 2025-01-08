package com.piotrkowalczykk.dormitory_management_app.security.model;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String passwordResetCode = null;
    private LocalDateTime passwordResetCodeExpiryDate = null;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;
    private LocalDate createdAt;
    private boolean emailVerified = false;
    private String emailVerificationCode = null;
    private LocalDateTime emailVerificationCodeExpiryDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Academy academy;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public AuthUser(long id, String email, String password, String passwordResetCode,
                    LocalDateTime passwordResetCodeExpiryDate, String firstName, String lastName,
                    Gender gender, LocalDate dateOfBirth, LocalDate createdAt, boolean emailVerified,
                    String emailVerificationCode, LocalDateTime emailVerificationCodeExpiryDate, Academy academy, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordResetCode = passwordResetCode;
        this.passwordResetCodeExpiryDate = passwordResetCodeExpiryDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.emailVerified = emailVerified;
        this.emailVerificationCode = emailVerificationCode;
        this.emailVerificationCodeExpiryDate = emailVerificationCodeExpiryDate;
        this.academy = academy;
        this.roles = roles;
    }

    public AuthUser(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    public LocalDateTime getPasswordResetCodeExpiryDate() {
        return passwordResetCodeExpiryDate;
    }

    public void setPasswordResetCodeExpiryDate(LocalDateTime passwordResetCodeExpiryDate) {
        this.passwordResetCodeExpiryDate = passwordResetCodeExpiryDate;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public LocalDateTime getEmailVerificationCodeExpiryDate() {
        return emailVerificationCodeExpiryDate;
    }

    public void setEmailVerificationCodeExpiryDate(LocalDateTime emailVerificationCodeExpiryDate) {
        this.emailVerificationCodeExpiryDate = emailVerificationCodeExpiryDate;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
