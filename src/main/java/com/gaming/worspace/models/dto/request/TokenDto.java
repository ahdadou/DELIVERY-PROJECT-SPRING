package com.gaming.worspace.models.dto.request;

public class TokenDto {

    String value;
    String email;
    String firstname;
    String lastname;
    String image;
    boolean newAccount=false;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public TokenDto(String value, String email, String firstname, String lastname, String image) {
//        this.value = value;
//        this.email = email;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.image = image;
//    }
//
//    public TokenDto(String value) {
//        super();
//        this.value = value;
//    }

    public boolean isNewAccount() {
        return newAccount;
    }

    public void setNewAccount(boolean newAccount) {
        this.newAccount = newAccount;
    }

    public TokenDto() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}