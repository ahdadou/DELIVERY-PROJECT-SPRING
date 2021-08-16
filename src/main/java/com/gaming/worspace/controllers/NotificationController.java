package com.gaming.worspace.controllers;


import com.gaming.worspace.models.Notification;
import com.gaming.worspace.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/notification")
public class NotificationController {


    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping("/email")
    public ResponseEntity getByEmail(@PathParam("email") String email){
        List<Notification> notificationList = this.notificationService.getListOfNotifications(email);
        return ResponseEntity.ok(notificationList);
    }



}
