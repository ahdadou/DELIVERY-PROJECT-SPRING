package com.gaming.worspace.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ReviewException extends RuntimeException {

    private final String user_sender;
    private final String user_receiver;

    public ReviewException(String user_sender, String user_receiver) {
        super(String.format("Failed to send Review User[%s] to User[%s]", user_sender, user_receiver));
        this.user_sender = user_sender;
        this.user_receiver = user_receiver;
    }

}
