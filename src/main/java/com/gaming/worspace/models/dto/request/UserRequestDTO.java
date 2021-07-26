package com.gaming.worspace.models.dto.request;

import com.gaming.worspace.models.Role;
import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.Column;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
public class UserRequestDTO {

    @NotNull
    private String username;

    private String firstname;

    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Instant birthday;
    private String phone;
    private Set<String> rolesString = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getBirthday() {
        return birthday;
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

    public Set<String> getRolesString() {
        return rolesString;
    }

    public void setRolesString(Set<String> rolesString) {
        this.rolesString = rolesString;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", roles='" + rolesString + '\'' +
                '}';
    }
}
