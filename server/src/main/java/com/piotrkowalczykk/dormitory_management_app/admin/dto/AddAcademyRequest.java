package com.piotrkowalczykk.dormitory_management_app.admin.dto;

public class AddAcademyRequest {
    private String name;
    private String description;
    private String logo;
    private String city;
    private String address;
    private String postalCode;
    private String country;
    private String phone;
    private String website;
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
