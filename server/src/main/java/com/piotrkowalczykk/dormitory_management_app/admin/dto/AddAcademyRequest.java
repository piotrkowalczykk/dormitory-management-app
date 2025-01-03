package com.piotrkowalczykk.dormitory_management_app.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddAcademyRequest {
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "logo is mandatory")
    private String logo;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "address is mandatory")
    private String address;
    @NotBlank(message = "postal code is mandatory")
    private String postalCode;
    @NotBlank(message = "country is mandatory")
    private String country;
    @NotBlank(message = "phone number is mandatory")
    @Pattern(regexp = "^[0-9]{9,15}$", message = "phone number must be between 9 and 15 digits")
    private String phone;
    @NotBlank(message = "website address is mandatory")
    private String website;
    @NotBlank(message = "email address is mandatory")
    @Email(message = "email address is not valid")
    private String email;

    public AddAcademyRequest(String name, String description, String logo, String city, String address, String postalCode, String country, String phone, String website, String email) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.website = website;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
