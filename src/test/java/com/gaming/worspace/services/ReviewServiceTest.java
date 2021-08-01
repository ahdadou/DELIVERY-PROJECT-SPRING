//package com.gaming.worspace.services;
//
//
//import com.gaming.worspace.dao.ReviewRepository;
//import com.gaming.worspace.models.Review;
//import com.gaming.worspace.models.dto.request.ReviewRequest;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class ReviewServiceTest {
//
//    @InjectMocks
//    private ReviewService underTest;
//    @Mock
//    private ReviewRepository reviewRepository;
//    @Mock
//    private UserServices userServices;
//
//
//    @BeforeEach
//    void setUp() {
//        underTest = new ReviewService(reviewRepository,userServices);
//    }
//
//
//
//    private ReviewRequest getReviewRequest(){
//        ReviewRequest r = new ReviewRequest();
//        r.setBody("test");
//        r.setRating(3);
//        return r;
//    }
//
//}
