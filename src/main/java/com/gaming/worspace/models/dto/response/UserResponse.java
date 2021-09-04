package com.gaming.worspace.models.dto.response;

import com.gaming.worspace.models.City;
import com.gaming.worspace.models.Review;
import com.gaming.worspace.models.enumerated.Gender;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class UserResponse {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private String image;
    private Gender gender;
    private String city;
    private String Country;
    private int avergeRating;
    private boolean isCompteVerifie;
    private Instant birthday;
    private String phone;

    private List<ReviewResponse> reviews = new ArrayList<>();


    public Instant getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCompteVerifie() {
        return isCompteVerifie;
    }

    public void setCompteVerifie(boolean compteVerifie) {
        isCompteVerifie = compteVerifie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }

    public int getAvergeRating() {
        return avergeRating;
    }

    public void setAvergeRating(int avergeRating) {
        this.avergeRating = avergeRating;
    }


}
