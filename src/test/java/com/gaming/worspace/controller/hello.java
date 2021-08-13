//package com.gaming.worspace.controller;
//
//
//import com.gaming.worspace.controllers.ReviewController;
//import com.gaming.worspace.models.Review;
//import com.gaming.worspace.services.ReviewService;
//import javafx.beans.binding.When;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ReviewController.class)
//public class hello {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ReviewService reviewService;
//
////    @Test
////    void helloWordeTst() throws Exception {
////
////        When(reviewService.getReview()).thenreturn(new Review(1,2,3,"ss"))
////
////        RequestBuilder request = MockMvcRequestBuilders
////                .get("/hello-world")
////                .accept(MediaType.APPLICATION_JSON);
////
////        MvcResult result = mockMvc.perform(request)
////                .andExpect(status().is(200))
////                .andExpect(content().json("{\"id\":1,\"name\":\"ball\"}"))
////                .andReturn();
////
////    }
////
//
//
//
//}
