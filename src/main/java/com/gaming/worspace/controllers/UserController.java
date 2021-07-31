package com.gaming.worspace.controllers;

import com.gaming.worspace.exceptions.UserRegistrationException;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.dto.response.ApiResponse;
import com.gaming.worspace.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    //        CREATE USER
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO){

        return userServices.createUser(userRequestDTO)
                .map(
                       user ->{
                           return ResponseEntity.ok(new ApiResponse(true, "User registered successfully. Check your email for verification"));
                       })
                .orElseThrow(() -> new UserRegistrationException(userRequestDTO.getEmail(), "Missing user object in database"));
    }



    //  GET ALL USERS

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        List<User> users = userServices.getAllUser();
        return ResponseEntity.ok(users);

    }

    //    GET USER BY USERNAME
   

    //    GET USER BY EMAIL
    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@PathParam("email") String email){
        User user = userServices.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    //    GET ALL DELIVRYMAN
    @GetMapping("/Service")
    public ResponseEntity<?> getAllUserByServiceType(@PathParam("SERVICETYPE") String SERVICETYPE){
        List<User> users = userServices.getAllUserBySERVICETYPE(SERVICETYPE);
        return ResponseEntity.ok(users);

    }



//

//    DELETE BY USER_ID









}
