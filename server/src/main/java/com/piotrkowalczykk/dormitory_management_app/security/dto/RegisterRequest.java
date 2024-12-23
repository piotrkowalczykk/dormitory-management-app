package com.piotrkowalczykk.dormitory_management_app.security.dto;

import com.piotrkowalczykk.dormitory_management_app.security.model.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RegisterRequest {

    @NotBlank(message = "email address is mandatory")
    @Email(message = "email address is not valid")
    private String email;
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;
    @NotBlank(message = "first name is mandatory")
    private String firstName;
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    @NotNull(message = "gender is mandatory")
    private Gender gender;
    @NotNull(message = "date of birth is mandatory")
    @Past(message = "date of birth is not valid")
    private LocalDate dateOfBirth;

    public RegisterRequest(String email, String password, String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;;
        this.dateOfBirth = dateOfBirth;
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
}
