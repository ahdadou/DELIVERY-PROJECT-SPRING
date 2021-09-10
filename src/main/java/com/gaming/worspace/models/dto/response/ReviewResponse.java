package com.gaming.worspace.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaming.worspace.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor @AllArgsConstructor
public class ReviewResponse {
    public long  ID;

    public UserResponse user_sender;

    private int rating;

    private String body;

    private Instant createAt;

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public UserResponse getUser_sender() {
        return user_sender;
    }

    public void setUser_sender(UserResponse user_sender) {
        this.user_sender = user_sender;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
