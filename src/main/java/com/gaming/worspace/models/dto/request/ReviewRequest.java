package com.gaming.worspace.models.dto.request;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {


    public String username_receiver;

    public String username_sender;

    private int rating;

    private String body;


    public String getUsername_receiver() {
        return username_receiver;
    }

    public void setUsername_receiver(String username_receiver) {
        this.username_receiver = username_receiver;
    }

    public String getUsername_sender() {
        return username_sender;
    }

    public void setUsername_sender(String username_sender) {
        this.username_sender = username_sender;
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
