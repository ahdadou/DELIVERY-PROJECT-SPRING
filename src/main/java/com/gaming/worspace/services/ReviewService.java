package com.gaming.worspace.services;


import com.gaming.worspace.dao.ReviewRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Review;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.ReviewRequest;
import com.gaming.worspace.models.dto.response.ReviewResponse;
import com.gaming.worspace.services.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserServices userServices;
    private ReviewMapper reviewMapper;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserServices userServices, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userServices = userServices;
        this.reviewMapper = reviewMapper;
    }



    //todo: add review
    public Optional<Review> addReview(ReviewRequest reviewRequest){


        Review review = reviewRepository.findByUser_receiverEmailAndUser_senderEmail(reviewRequest.getEmail_receiver(),reviewRequest.getEmail_sender());

        if(review!=null){
            review.setBody(reviewRequest.getBody());
            review.setRating(reviewRequest.getRating());
        }else{
            review=new Review();
            User senderUser = userServices.getUserByEmail(reviewRequest.getEmail_sender());
            User receiverUser = userServices.getUserByEmail(reviewRequest.getEmail_receiver());
            review.setBody(reviewRequest.getBody());
            review.setRating(reviewRequest.getRating());
            review.setUser_sender(senderUser);
            review.setUser_receiver(receiverUser);
            updateUserReview(receiverUser,reviewRequest);
        }

        return Optional.ofNullable(reviewRepository.save(review));
    }


    public List<Review> getByEmail(String email) {
      return  this.reviewRepository.findByUser_receiverEmail(email)
                .orElseThrow(()->new NotFoundException("Role Not Found"));

    }


    public List<ReviewResponse> getReviewResponseByEmail(String email) {
        List<Review> reviews=  this.reviewRepository.findByUser_receiverEmail(email)
                .orElseThrow(()->new NotFoundException("Role Not Found"));

        return reviewMapper.toReviewReponse(reviews);

    }



    public void updateUserReview(User user,ReviewRequest reviewRequest){
        user.setReviewCount(user.getReviewCount()+1);
        double average = (user.getRatingAverage()+reviewRequest.getRating())/(user.getReviewCount()+1);
        user.setRatingAverage((int)average);

        userServices.save(user);
    }
}
