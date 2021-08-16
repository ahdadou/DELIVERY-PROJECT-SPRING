package com.gaming.worspace.controllers;


import com.gaming.worspace.exceptions.ReviewException;
import com.gaming.worspace.models.Notification;
import com.gaming.worspace.models.Review;
import com.gaming.worspace.models.dto.request.ReviewRequest;
import com.gaming.worspace.models.dto.response.ApiResponse;
import com.gaming.worspace.services.NotificationService;
import com.gaming.worspace.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/review")
public class ReviewController {

    private ReviewService reviewService;
    private NotificationService notificationService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ReviewController(ReviewService reviewService, NotificationService notificationService, SimpMessagingTemplate simpMessagingTemplate) {
        this.reviewService = reviewService;
        this.notificationService = notificationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }



    @MessageMapping("/post")
    public void sendReview(
            @Payload ReviewRequest reviewRequest) throws Exception {

//        reviewService.addReview(reviewRequest);
        simpMessagingTemplate.convertAndSendToUser(
                reviewRequest.email_receiver, "/queue/notifications",reviewRequest);
    }


    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody ReviewRequest reviewRequest){
        return reviewService.addReview(reviewRequest)
                .map(
                        user ->{
                            // add review to notofication table
                            Notification notification = new Notification();
                            notification.setUser(user.getUser_receiver());
                            notification.setUser_sender(user.getUser_sender());
                            notification = notificationService.createNotification(notification);
                            //send a notification
                            simpMessagingTemplate.convertAndSendToUser(
                                    reviewRequest.email_receiver, "/queue/review",notification);

                            return ResponseEntity.ok(new ApiResponse(true, "Review Add successfully"));
                        })
                .orElseThrow(() -> new ReviewException(reviewRequest.getEmail_sender(), reviewRequest.getEmail_receiver()));

    }



    @GetMapping("/email")
    public ResponseEntity<?> getReviewByEmail(@PathParam("email") String email){
        List<Review> reviews= this.reviewService.getByEmail(email);
        return ResponseEntity.ok(reviews);
    }









}
