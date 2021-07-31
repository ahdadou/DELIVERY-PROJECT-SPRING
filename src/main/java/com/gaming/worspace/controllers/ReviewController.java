package com.gaming.worspace.controllers;


import com.gaming.worspace.exceptions.UserRegistrationException;
import com.gaming.worspace.models.dto.request.ReviewRequest;
import com.gaming.worspace.models.dto.response.ApiResponse;
import com.gaming.worspace.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }



    @MessageMapping("/post")
    public void sendReview(
            @Payload ReviewRequest reviewRequest) throws Exception {

        reviewService.addReview(reviewRequest);
        simpMessagingTemplate.convertAndSendToUser(
                reviewRequest.email_receiver, "/queue/notifications",reviewRequest);
    }


    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody ReviewRequest reviewRequest){
        return reviewService.addReview(reviewRequest)
                .map(
                        user ->{
                            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully. Check your email for verification"));
                        })
                .orElseThrow(() -> new UserRegistrationException(reviewRequest.getEmail_sender(), "Missing user object in database"));
    }







}
