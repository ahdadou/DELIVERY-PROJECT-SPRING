package com.gaming.worspace.services;


import com.gaming.worspace.dao.ReviewRepository;
import com.gaming.worspace.models.Review;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserServices userServices;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserServices userServices) {
        this.reviewRepository = reviewRepository;
        this.userServices = userServices;
    }



    //todo: add review
    public Review addReview(ReviewRequest reviewRequest){
        User senderUser = userServices.getUserByUsername(reviewRequest.getUsername_sender());
        User receiverUser = userServices.getUserByUsername(reviewRequest.getUsername_receiver());

        Review review = new Review();
        review.setBody(reviewRequest.getBody());
        review.setRating(reviewRequest.getRating());
//        review.setUser_sender(senderUser);
//        review.setUser_receiver(receiverUser);

        return reviewRepository.save(review);
    }







}
