package com.gaming.worspace.controllers;


import com.gaming.worspace.models.Notification;
import com.gaming.worspace.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/notification")
public class NotificationController {


    @Autowired
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping("/email")
    public ResponseEntity getByEmail(@PathParam("email") String email){
        List<Notification> notificationList = this.notificationService.getListOfNotifications(email);
        return ResponseEntity.ok(notificationList);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteById(@PathParam("id") long id){
         this.notificationService.deleteById(id);
        return ResponseEntity.ok(true);
    }


}
