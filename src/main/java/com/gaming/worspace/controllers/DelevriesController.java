package com.gaming.worspace.controllers;

import com.gaming.worspace.models.dto.response.UserResponse;
import com.gaming.worspace.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/deliveries")
public class DelevriesController {

    private UserServices userServices;

    @Autowired
    public DelevriesController(UserServices userServices) {
        this.userServices = userServices;
    }

    //    GET ALL DELIVRYMAN
    @GetMapping
    public ResponseEntity<?> getAllDeliveries(){
        List<UserResponse> users = userServices.getAllUserDelevriesDto();
        return ResponseEntity.ok(users);
    }


//    // Get Delevries By City
//    @GetMapping("/city/{city}")
//    public ResponseEntity<?> getAllDeliveriesByCity(@PathVariable("city") String city){
//        List<UserResponse> users = userServices.getAllUserDelevriesByCity(city);
//        return ResponseEntity.ok(users);
//    }
//
//
//    // Get Delevries By Rating
//
//    @GetMapping("/city/{city}")
//    public ResponseEntity<?> getAllDeliveriesByRating(@PathVariable("city") String city){
//        List<UserResponse> users = userServices.getAllUserDelevriesByCity(city);
//        return ResponseEntity.ok(users);
//    }



    @GetMapping("/filter")
    public ResponseEntity<?> getAllDeliveriesByCityAndRating(@PathParam("rating")int rating,@PathParam("city") String city){
        List<UserResponse> users=null;
        users = userServices.findByCityNameAndRatingAverageQuery(city,rating);
        return ResponseEntity.ok(users);
    }






}
